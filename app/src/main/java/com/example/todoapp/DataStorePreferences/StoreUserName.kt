package com.example.todoapp.DataStorePreferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

class StoreUserName(private val context : Context) {


}

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
    context: Context
) {
    
}
