package com.example.todoapp.LocalData

import kotlinx.coroutines.flow.Flow

class ReadUserName(
    private val localUserManager : LocalUserManager
) {

    suspend operator fun invoke() : Flow<String> {
        return localUserManager.readUserName()
    }
}