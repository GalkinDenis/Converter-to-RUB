package ru.denis.convertertorub.data.datasources.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.denis.convertertorub.data.model.CodeAndValueCurrencyEntity
import ru.denis.convertertorub.data.model.CurrenciesEntity

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(item: CurrencyEntityTable)

    @Query("SELECT * from currency_entity")
    fun getCurrencies(): Flow<List<CurrencyEntityTable>>

    @Query("SELECT CharCode, Value FROM currency_entity WHERE name = :nameItem")
    suspend fun getCodeAndValueCurrency(nameItem: String): CodeAndValueCurrencyEntity

}