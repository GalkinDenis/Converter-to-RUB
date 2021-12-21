package ru.denis.convertertorub.domain.usecases

import retrofit2.Response
import ru.denis.convertertorub.domain.entity.Currencies
import ru.denis.convertertorub.domain.repository.RemoteRepository
import javax.inject.Inject

class GetCurrenciesFromRemoteUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {

    suspend operator fun invoke() =
        remoteRepository.getCurrenciesFromRemote()

}