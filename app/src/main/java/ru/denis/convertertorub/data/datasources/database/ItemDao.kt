package ru.denis.convertertorub.data.datasources.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert
    suspend fun insert(item: CurrencyEntityDataBase)

    @Update
    suspend fun update(item: CurrencyEntityDataBase)

    @Query("SELECT * from CurrencyTable")
    suspend fun getItems(): Flow<List<CurrencyEntityDataBase>>

    @Query("SELECT * FROM CurrencyTable WHERE name = :nameItem")
    suspend fun getValue(nameItem: String): Flow<CurrencyEntityDataBase>

    @Query("DELETE FROM CurrencyTable")
    suspend fun clearTable()

}