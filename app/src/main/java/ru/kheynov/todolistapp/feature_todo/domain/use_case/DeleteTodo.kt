package ru.kheynov.todolistapp.feature_todo.domain.use_case

import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.repository.TodoRepository

class DeleteTodo(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo){
        repository.deleteTodo(todo)
    }
}