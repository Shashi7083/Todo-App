package com.example.todoapp.LocalData

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.util.Constants
import com.example.todoapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    override suspend fun saveUserName(name : String) {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.USER_NAME] =  name
        }
    }

    override fun readUserName(): Flow<String> {
        return context.dataStore.data.map {preferences ->
            preferences[PreferencesKeys.USER_NAME]?: "User"
        }
    }
}

private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys{
    val USER_NAME = stringPreferencesKey(name = Constants.USER_NAME)
}