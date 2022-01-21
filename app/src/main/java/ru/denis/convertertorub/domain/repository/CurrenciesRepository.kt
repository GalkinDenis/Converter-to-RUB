package ru.denis.convertertorub.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable
import ru.denis.convertertorub.data.model.CodeAndValueCurrencyEntity
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.entities.Currency

interface CurrenciesRepository {
    suspend fun getCurrencies(): List<Currency>?
    suspend fun saveCurrencies(currencies: List<Currency>?)
    suspend fun getSavedDate(): String
    suspend fun loadAllCurrencies(): Flow<List<CurrencyEntityTable>>
    suspend fun getCodeAndValueCurrency(targetCurrencyName: String): CodeAndValueCurrency
    suspend fun checkOnline(): Boolean
    suspend fun saveCurrentDate(currentDate: String?)
}