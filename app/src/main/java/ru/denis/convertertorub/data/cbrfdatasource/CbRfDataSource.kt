package ru.denis.convertertorub.data.cbrfdatasource

import retrofit2.Response
import ru.denis.convertertorub.domain.entity.Currencies

interface CbRfDataSource {

    suspend fun getCurrenciesFromRemote(): Response<Currencies>

}