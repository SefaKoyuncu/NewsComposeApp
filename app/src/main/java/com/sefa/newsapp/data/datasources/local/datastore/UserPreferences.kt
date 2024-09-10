package com.sefa.newsapp.data.datasources.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val IS_REMEMBER_ME = booleanPreferencesKey("is_remember_me")
        private val USER_EMAIL = stringPreferencesKey("user_email")
    }

    suspend fun setRememberMe(isRememberMe: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_REMEMBER_ME] = isRememberMe
        }
    }

    suspend fun isRememberMe(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[IS_REMEMBER_ME] ?: false
        }.first()
    }

    suspend fun setUserEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }

    suspend fun getUserEmail(): String? {
        return dataStore.data.map { preferences ->
            preferences[USER_EMAIL]
        }.firstOrNull()
    }

    suspend fun clearAllPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}