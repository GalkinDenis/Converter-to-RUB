package ru.denis.convertertorub.data.datasources.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(item: CurrencyEntity)

    @Update
    suspend fun update(item: CurrencyEntity)

    @Query("SELECT * from currency_entity")
    fun getCurrencies(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currency_entity WHERE name = :nameItem")
    fun getValue(nameItem: String): Flow<CurrencyEntity>

    @Query("DELETE FROM currency_entity")
    suspend fun clearTable()

}