package com.example.todoapp.LocalData

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveUserName( name :String)

    fun readUserName(): Flow<String>
}