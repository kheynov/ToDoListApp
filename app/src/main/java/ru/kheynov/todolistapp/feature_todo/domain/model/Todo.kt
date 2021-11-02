package ru.kheynov.todolistapp.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    val isChecked: Boolean,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
)

class InvalidTodoException(message: String) : Exception(message)