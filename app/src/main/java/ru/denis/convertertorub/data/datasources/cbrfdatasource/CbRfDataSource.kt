package ru.denis.convertertorub.data.datasources.cbrfdatasource

import retrofit2.Response
import ru.denis.convertertorub.data.model.CurrenciesEntity

interface CbRfDataSource {
    suspend fun getCurrencies(): Response<CurrenciesEntity>
}