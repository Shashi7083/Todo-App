package com.example.todoapp

import android.app.Application
import androidx.room.Room
import com.example.todoapp.QuickListDatabase.TodoDatabase
import com.example.todoapp.repository.TodoRepo
import com.example.todoapp.repository.TodoRepoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.dsl.bind 


class TodoApp : Application(){

    override fun onCreate() {
        super.onCreate()


        startKoin{

            modules(module {
                single{
                   Room.databaseBuilder(this@TodoApp, TodoDatabase::class.java, "db").build()
                }
                single{
                    TodoRepoImpl(database = get())
                } bind TodoRepo::class

          })
        }
    }

}

