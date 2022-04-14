package ru.denis.convertertorub.data.datasources.preferencedatasource

import javax.inject.Inject
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
@BoundTo(PreferenceDatasource::class, SingletonComponent::class)
class PreferenceDatasourceImpl @Inject constructor(
    private val prefsDataStore: DataStore<Preferences>
) : PreferenceDatasource {

    override suspend fun saveCurrentDate(date: String?, key: String) {
        withContext(Dispatchers.IO) {
            date?.let {
                prefsDataStore.edit { prefs ->
                    prefs[stringPreferencesKey(key)] = date
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