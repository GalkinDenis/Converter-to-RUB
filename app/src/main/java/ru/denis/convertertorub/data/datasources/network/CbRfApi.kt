package ru.denis.convertertorub.data.datasources.network

import retrofit2.Response
import retrofit2.http.*
import ru.denis.convertertorub.data.model.CurrenciesEntity

interface CbRfApi {
    @GET("{path}")
    suspend fun getCurrencies(@Path("path") path: String): Response<CurrenciesEntity>
}