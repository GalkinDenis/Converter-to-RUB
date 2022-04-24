package ru.denis.convertertorub.data.datasources.localdatasource

import kotlinx.coroutines.flow.Flow
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable
import ru.denis.convertertorub.data.model.CodeAndValueCurrencyEntity

interface LocalDataSource {
    suspend fun loadAllCurrencies(): Flow<List<CurrencyEntityTable>>
    suspend fun saveCurrencies(readyCurrencies: List<CurrencyEntityTable>?)
    suspend fun saveCurrentDate(currentDate: String?)
    suspend fun getSavedDate(): String
    suspend fun getCodeAndValueCurrency(targetCurrencyName: String): CodeAndValueCurrencyEntity
}