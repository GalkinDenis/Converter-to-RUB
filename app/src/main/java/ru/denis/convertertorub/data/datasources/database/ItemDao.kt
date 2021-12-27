package ru.denis.convertertorub.data.datasources.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: CurrencyEntity)

    @Update
    suspend fun update(item: CurrencyEntity)

    @Query("SELECT * from currencyEntity")
    fun getCurrencies(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currencyEntity WHERE name = :nameItem")
    fun getValue(nameItem: String): Flow<CurrencyEntity>

    @Query("DELETE FROM currencyEntity")
    suspend fun clearTable()

}