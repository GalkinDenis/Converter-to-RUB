package ru.denis.convertertorub.domain.repository

import retrofit2.Response
import ru.denis.convertertorub.data.model.Currencies

interface CurrenciesRepository {
    suspend fun getCurrencies(): Response<Currencies>
    suspend fun saveCurrencies(responseBody: Response<Currencies>)
}