package ru.denis.convertertorub.domain.usecases

import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class GetNetworkStatusUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository
) {
    suspend operator fun invoke() = currenciesRepository.isOnline()
}