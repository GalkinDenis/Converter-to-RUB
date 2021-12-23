package ru.denis.convertertorub.data.datasources.dbdatasource

import retrofit2.Response
import ru.denis.convertertorub.data.model.Currencies

interface DbDataSource {
    suspend fun saveCurrencies(responseBody: Response<Currencies>)
}