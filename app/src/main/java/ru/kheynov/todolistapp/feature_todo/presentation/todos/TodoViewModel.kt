package ru.kheynov.todolistapp.feature_todo.presentation.todos

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.kheynov.todolistapp.feature_todo.domain.model.InvalidTodoException
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.use_case.TodosUseCases
import ru.kheynov.todolistapp.feature_todo.domain.util.OrderType
import ru.kheynov.todolistapp.feature_todo.domain.util.TodoOrder
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todosUseCases: TodosUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TodosState())
    val state: State<TodosState> = _state
    private var recentlyDeletedTodo: Todo? = null

    private var getTodosJob: Job? = null

    init {
        getTodos(TodoOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: TodosEvent) {
        when (event) {
            is TodosEvent.Order -> {
                if (state.value.todoOrder::class == event.todoOrder::class &&
                    state.value.todoOrder.orderType == event.todoOrder.orderType
                ) {
                    return
                }
                getTodos(event.todoOrder)
            }
            is TodosEvent.DeleteTodo -> {
                viewModelScope.launch {
                    todosUseCases.deleteTodo(event.todo)
                    recentlyDeletedTodo = event.todo
                }
            }
            is TodosEvent.RestoreTodo -> {
                viewModelScope.launch {
                    try {
                        todosUseCases.addTodo(recentlyDeletedTodo ?: return@launch)
                        recentlyDeletedTodo = null
                    } catch (e: Exception) {
                        Log.e("ERROR", e.stackTraceToString())
                    }
                }
            }
            is TodosEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }
            is TodosEvent.SaveTodo -> {
                viewModelScope.launch {
                    try {
                        todosUseCases.addTodo(
                            Todo(
                                title = event.todo.title,
                                isChecked = event.todo.isChecked,
                                id = event.todo.id,
                                timestamp = System.currentTimeMillis(),
                            )
                        )
                    } catch (e: InvalidTodoException) {
                        Log.e("ERROR", e.stackTraceToString())
                    }
                }
            }
        }
    }

    private fun getTodos(todoOrder: TodoOrder) {
        getTodosJob?.cancel()
        getTodosJob = todosUseCases.getTodos(todoOrder)
            .onEach { todos ->
                _state.value = state.value.copy(
                    todos = todos,
                    todoOrder = todoOrder
                )
            }.launchIn(viewModelScope)
    }
}