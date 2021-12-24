package ru.denis.convertertorub.data.datasources.dbdatasource

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.model.Currencies

interface DbDataSource {
    suspend fun saveCurrencies(responseBody: Response<Currencies>)
    suspend fun loadAllCurrencies(): Flow<List<CurrencyEntity>>
}