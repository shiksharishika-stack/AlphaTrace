package com.alphatrace.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "alphatrace_progress")

class ProgressRepository(private val context: Context) {

    private fun starKey(letter: Char): Preferences.Key<Int> =
        intPreferencesKey("star_$letter")

    fun getStars(letter: Char): Flow<Int> =
        context.dataStore.data.map { prefs ->
            prefs[starKey(letter)] ?: 0
        }

    fun getAllStars(): Flow<Map<Char, Int>> =
        context.dataStore.data.map { prefs ->
            ('A'..'Z').associateWith { letter ->
                prefs[starKey(letter)] ?: 0
            }
        }

    suspend fun saveStars(letter: Char, stars: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[starKey(letter)] ?: 0
            if (stars > current) {
                prefs[starKey(letter)] = stars
            }
        }
    }
}
