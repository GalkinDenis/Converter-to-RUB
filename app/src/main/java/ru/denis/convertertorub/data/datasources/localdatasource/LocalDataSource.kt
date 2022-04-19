package ru.denis.convertertorub.data.datasources.localdatasource

import kotlinx.coroutines.flow.Flow
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable
import ru.denis.convertertorub.data.model.CodeAndValueCurrencyEntity
import ru.denis.convertertorub.data.model.CurrencyEntity

interface LocalDataSource {
    suspend fun loadAllCurrencies(): Flow<List<CurrencyEntityTable>>
    suspend fun saveCurrencies(currency: CurrencyEntity)
    fun trimSize(value: Double): String
    fun getPrefixValue(value: Double): String
    suspend fun saveCurrentDate(currentDate: String?)
    suspend fun getSavedDate(): String
    suspend fun getCodeAndValueCurrency(targetCurrencyName: String): CodeAndValueCurrencyEntity
}