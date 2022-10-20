package ru.denis.convertertorub.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.domain.entities.ResponseFromRequest

interface CurrenciesRepository {
    suspend fun saveCurrencies(readyCurrencies: List<ReadyCurrencies>?)
    suspend fun getSavedDate(): String
    suspend fun getDirtyCurrencies(): ResponseFromRequest
    suspend fun loadAllCurrencies(): Flow<List<ReadyCurrencies>>
    suspend fun getCodeAndValueCurrency(targetCurrencyName: String): CodeAndValueCurrency
    suspend fun saveCurrentDate(currentDate: String?)
}