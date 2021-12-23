package ru.denis.convertertorub.data.datasources.cbrfdatasource

import retrofit2.Response
import ru.denis.convertertorub.domain.entity.Currencies

interface CbRfDataSource {
    suspend fun getCurrencies(): Response<Currencies>
}