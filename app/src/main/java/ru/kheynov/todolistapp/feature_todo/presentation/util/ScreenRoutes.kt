package ru.kheynov.todolistapp.feature_todo.presentation.util

sealed class ScreenRoutes(val route: String) {
    object TodosScreen : ScreenRoutes("todos_screen")
    object AddEditTodoScreen : ScreenRoutes("add_edit_todo_screen")
}