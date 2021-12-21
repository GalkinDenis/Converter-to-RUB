package ru.denis.convertertorub.domain.repository

import retrofit2.Response
import ru.denis.convertertorub.domain.entity.Currencies

interface RemoteRepository {

    suspend fun getCurrenciesFromRemote(): Response<Currencies>

}