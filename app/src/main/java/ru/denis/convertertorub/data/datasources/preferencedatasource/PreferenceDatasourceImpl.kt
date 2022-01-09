package ru.denis.convertertorub.data.datasources.preferencedatasource

import javax.inject.Inject
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PreferenceDatasourceImpl @Inject constructor(
    private val prefsDataStore: DataStore<Preferences>
) : PreferenceDatasource {

    override suspend fun saveCurrentDate(date: String?, key: String) {
        withContext(Dispatchers.IO) {
            date?.let {
                prefsDataStore.edit { prefs ->
                    prefs[stringPreferencesKey(key)] = "2022-01-09"
                }
            }
        }
    }

    override suspend fun getSavedDate(key: String): String =
        withContext(Dispatchers.IO) {
            prefsDataStore.data.map { prefs ->
                prefs[stringPreferencesKey(key)] ?: ""
            }.first()
        }

}