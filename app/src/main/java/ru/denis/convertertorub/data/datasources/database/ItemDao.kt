package ru.denis.convertertorub.data.datasources.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrencies(item: CurrencyEntity)

    @Update
    suspend fun update(item: CurrencyEntity)

    @Query("SELECT * from currency_entity")
    fun getCurrencies(): Flow<List<CurrencyEntity>>

    @Query("SELECT CharCode, Value FROM currency_entity WHERE name = :nameItem")
    suspend fun getCodeAndValueCurrency(nameItem: String): CodeAndValueCurrency

}