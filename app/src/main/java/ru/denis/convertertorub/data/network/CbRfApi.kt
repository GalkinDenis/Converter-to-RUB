package ru.denis.convertertorub.data.network

import retrofit2.Response
import retrofit2.http.*
import ru.denis.convertertorub.domain.entity.Currencies

interface CbRfApi {

    @GET("/{path}")
    suspend fun getCurrencies(@Path("path") path: String): Response<Currencies>

}