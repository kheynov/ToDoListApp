package ru.kheynov.todolistapp.feature_todo.presentation.todos

import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.util.TodoOrder

sealed class TodosEvent {
    data class Order(val todoOrder: TodoOrder) : TodosEvent()
    data class DeleteTodo(val todo: Todo): TodosEvent()
    object RestoreTodo: TodosEvent()
    object ToggleOrderSection: TodosEvent()
    data class SaveTodo(val todo: Todo): TodosEvent()
}
