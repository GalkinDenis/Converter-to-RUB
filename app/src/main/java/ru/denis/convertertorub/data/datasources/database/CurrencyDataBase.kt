package ru.denis.convertertorub.data.datasources.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntityTable::class], version = 1, exportSchema = false)
abstract class CurrencyDataBase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}