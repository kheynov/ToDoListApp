package ru.kheynov.todolistapp.feature_todo.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.repository.TodoRepository
import ru.kheynov.todolistapp.feature_todo.domain.util.TodoOrder
import ru.kheynov.todolistapp.feature_todo.domain.util.OrderType

class GetTodos(
    private val repository: TodoRepository
) {
    operator fun invoke(
        todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending)
    ): Flow<List<Todo>> {
        return repository.getTodos().map { todos ->
            if (todoOrder.orderType == OrderType.Ascending){
                when(todoOrder){
                    is TodoOrder.Title -> todos.sortedBy { it.title.lowercase() }
                    is TodoOrder.Date -> todos.sortedBy { it.timestamp }
                }
            }else{
                when(todoOrder){
                    is TodoOrder.Title -> todos.sortedByDescending { it.title.lowercase() }
                    is TodoOrder.Date -> todos.sortedByDescending { it.timestamp }
                }
            }
        }
    }
}