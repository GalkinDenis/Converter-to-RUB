package ru.denis.convertertorub.domain.usecases

import ru.denis.convertertorub.presentation.CheckNetWork
import javax.inject.Inject

class GetNetworkStatusUseCase @Inject constructor(
    private val currenciesRepository: CheckNetWork
) {
    operator fun invoke() = currenciesRepository.checkOnline()
}