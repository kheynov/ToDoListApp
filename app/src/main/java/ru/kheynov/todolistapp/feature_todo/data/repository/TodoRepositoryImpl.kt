package ru.kheynov.todolistapp.feature_todo.data.repository

import kotlinx.coroutines.flow.Flow
import ru.kheynov.todolistapp.feature_todo.data.data_source.TodoDao
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository{
    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

}