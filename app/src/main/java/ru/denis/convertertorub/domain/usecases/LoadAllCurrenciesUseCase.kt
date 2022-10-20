package ru.denis.convertertorub.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import ru.denis.convertertorub.domain.usecases.baseusecases.FlowSharedUseCases
import javax.inject.Inject

@ViewModelScoped
class LoadAllCurrenciesUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val currenciesRepository: CurrenciesRepository
) : FlowSharedUseCases<ReadyCurrencies>(dispatcher) {

    override suspend fun execute() = currenciesRepository.loadAllCurrencies()
}