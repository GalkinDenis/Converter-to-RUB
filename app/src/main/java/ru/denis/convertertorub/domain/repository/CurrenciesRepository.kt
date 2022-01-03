package ru.denis.convertertorub.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.model.Currencies

interface CurrenciesRepository {
    suspend fun getCurrencies(): Response<Currencies>
    suspend fun saveCurrencies(responseBody: Response<Currencies>)
    suspend fun getSavedDate(): String
    suspend fun loadAllCurrencies(): Flow<List<CurrencyEntity>>
}