package ru.denis.convertertorub.domain.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency

interface CurrenciesRepository {
    suspend fun getCurrencies(): Response<Currencies>
    suspend fun saveCurrencies(responseBody: Response<Currencies>)
    suspend fun getSavedDate(): String
    suspend fun loadAllCurrencies(): Flow<List<CurrencyEntity>>
    suspend fun getCodeAndValueCurrency(targetCurrencyName: String): CodeAndValueCurrency
    suspend fun isOnline(): Boolean
}