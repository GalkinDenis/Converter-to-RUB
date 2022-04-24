package ru.denis.convertertorub.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.entities.ResponseFromRequest
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import ru.denis.convertertorub.domain.usecases.baseusecases.OutSharedUseCase
import javax.inject.Inject

class GetDirtyCurrenciesUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val currenciesRepository: CurrenciesRepository
) : OutSharedUseCase<ResponseFromRequest>(dispatcher) {
    override suspend fun execute() = currenciesRepository.getDirtyCurrencies()
}