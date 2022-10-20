package ru.denis.convertertorub.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import ru.denis.convertertorub.domain.usecases.baseusecases.OutSharedUseCase
import javax.inject.Inject

@ViewModelScoped
class GetSavedDataUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val currenciesRepository: CurrenciesRepository
) : OutSharedUseCase<String>(dispatcher) {

    override suspend fun execute() = currenciesRepository.getSavedDate()
}
