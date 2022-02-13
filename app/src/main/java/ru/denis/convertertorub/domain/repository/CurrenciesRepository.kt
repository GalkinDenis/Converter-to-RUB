package ru.denis.convertertorub.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.denis.convertertorub.data.model.CurrencyEntity
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.entities.ReadyCurrencies

interface CurrenciesRepository {
    suspend fun getCurrencies()
    suspend fun getSavedDate(): String
    suspend fun loadAllCurrencies(): Flow<List<ReadyCurrencies>>
    suspend fun getCodeAndValueCurrency(targetCurrencyName: String): CodeAndValueCurrency
    suspend fun saveCurrentDate(currentDate: String?)
}