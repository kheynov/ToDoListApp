package ru.kheynov.todolistapp.feature_todo.presentation.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.kheynov.todolistapp.feature_todo.domain.model.InvalidTodoException
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.use_case.TodosUseCases
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todosUseCases: TodosUseCases,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _todoTitle = mutableStateOf(
        TodoTextFieldState(
            hint = ""
        )
    )
    val todoTitle: State<TodoTextFieldState> = _todoTitle

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTodoId: Int? = null

    init {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            if (todoId != -1) {
                viewModelScope.launch {
                    todosUseCases.getTodo(todoId)?.also { todo ->
                        currentTodoId = todo.id
                        _todoTitle.value = todoTitle.value.copy(
                            text = todo.title,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.EnteredTitle -> _todoTitle.value = _todoTitle.value.copy(
                text = event.value
            )
            is AddEditTodoEvent.ChangeTitleFocus -> _todoTitle.value = _todoTitle.value.copy(
                isHintVisible = !event.focusState.isFocused && _todoTitle.value.text.isBlank()
            )
            is AddEditTodoEvent.SaveTodo -> {
                viewModelScope.launch {
                    try {
                        todosUseCases.addTodo(
                            Todo(
                                title = todoTitle.value.text,
                                isChecked = false,
                                id = currentTodoId,
                                timestamp = System.currentTimeMillis()
                            )
                        )

                        _eventFlow.emit(UiEvent.SaveTodo)
                    } catch (e: InvalidTodoException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveTodo : UiEvent()
    }
}