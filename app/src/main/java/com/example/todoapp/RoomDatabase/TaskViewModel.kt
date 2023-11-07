package com.example.todoapp.RoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.Model.tasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskDatabase.getDatabase(application).taskDao()

    val tasks : Flow<List<tasks>> = taskDao.getAllTasks()

    fun insertTask(task : tasks){
        viewModelScope.launch {
            taskDao.insertTask(task)
        }
    }


    fun deleteTask(task : tasks){
        viewModelScope.launch {
            taskDao.deleteTask(task)
        }
    }

    fun updateTask(task : tasks){
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }
}