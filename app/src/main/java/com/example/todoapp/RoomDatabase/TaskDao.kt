package com.example.todoapp.RoomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.Model.tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks() : Flow<List<tasks>>

    @Insert
    suspend fun insertTask(task : tasks)

    @Delete
    suspend fun deleteTask(tasks: tasks)
}