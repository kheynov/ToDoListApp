package ru.kheynov.todolistapp.feature_todo.presentation.todos

import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.util.OrderType
import ru.kheynov.todolistapp.feature_todo.domain.util.TodoOrder

data class TodosState(
    val todos: List<Todo> = emptyList(),
    val todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)
