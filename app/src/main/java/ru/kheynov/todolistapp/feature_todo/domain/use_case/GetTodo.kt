package ru.kheynov.todolistapp.feature_todo.domain.use_case

import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.repository.TodoRepository

class GetTodo(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int): Todo? {
        return repository.getTodoById(id)
    }
}