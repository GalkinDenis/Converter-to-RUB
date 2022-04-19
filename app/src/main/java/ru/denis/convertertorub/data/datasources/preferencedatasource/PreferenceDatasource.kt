package ru.denis.convertertorub.data.datasources.preferencedatasource

interface PreferenceDatasource {
    suspend fun saveCurrentDate(date: String?, key: String)
    suspend fun getSavedDate(key: String): String
}