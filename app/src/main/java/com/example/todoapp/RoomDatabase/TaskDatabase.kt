package com.example.todoapp.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.Model.tasks

@Database(
    entities = [tasks::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object{
        @Volatile
        var INSTANCE : TaskDatabase? = null

        fun getDatabase(context : Context) : TaskDatabase{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    TaskDatabase::class.java,
                    "task_db"
                ).build()
                INSTANCE  = instance
                instance
            }
        }
    }
}