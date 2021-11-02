package ru.kheynov.todolistapp.feature_todo.domain.use_case

import ru.kheynov.todolistapp.feature_todo.domain.model.InvalidTodoException
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo
import ru.kheynov.todolistapp.feature_todo.domain.repository.TodoRepository

class AddTodo(
    private val repository: TodoRepository
) {

    @Throws(InvalidTodoException::class)
    suspend operator fun invoke(todo: Todo){
        if (todo.title.isBlank()){
            throw InvalidTodoException("The title cannot be empty")
        }
        repository.insertTodo(todo)
    }
}