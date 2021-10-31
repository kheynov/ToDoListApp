package ru.kheynov.todolistapp.feature_todo.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>

    suspend fun getTodoById(id: Int): Todo?

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)
}