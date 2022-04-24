package ru.denis.convertertorub.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import ru.denis.convertertorub.domain.usecases.baseusecases.InSharedUseCase
import javax.inject.Inject

class SaveDateUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val currenciesRepository: CurrenciesRepository
) : InSharedUseCase<String>(dispatcher) {
    override suspend fun execute(inParameter: String) {
        currenciesRepository.saveCurrentDate(inParameter)
    }
}

