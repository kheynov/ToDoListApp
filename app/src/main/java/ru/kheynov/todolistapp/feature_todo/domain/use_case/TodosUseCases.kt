package ru.kheynov.todolistapp.feature_todo.domain.use_case

data class TodosUseCases(
    val getTodos: GetTodos,
    val deleteTodo: DeleteTodo,
    val addTodo: AddTodo
)
