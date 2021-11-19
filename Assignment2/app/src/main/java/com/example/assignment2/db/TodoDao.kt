package com.example.assignment2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.assignment2.model.Todo


@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getAllTodo() : LiveData<List<Todo>>

    @Delete()
    suspend fun deleteTodo(todo:Todo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo : Todo)

}