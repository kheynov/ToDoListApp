package ru.kheynov.todolistapp.feature_todo.data.data_source

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.kheynov.todolistapp.feature_todo.domain.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: Int): Todo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}