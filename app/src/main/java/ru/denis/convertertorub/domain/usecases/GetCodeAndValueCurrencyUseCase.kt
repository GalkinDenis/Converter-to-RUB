package ru.denis.convertertorub.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import ru.denis.convertertorub.domain.usecases.baseusecases.InOutSharedUseCase
import javax.inject.Inject

class GetCodeAndValueCurrencyUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val currenciesRepository: CurrenciesRepository
) : InOutSharedUseCase<String, CodeAndValueCurrency>(dispatcher) {
    override suspend fun execute(parameter: String) =
        currenciesRepository.getCodeAndValueCurrency(parameter)
}





