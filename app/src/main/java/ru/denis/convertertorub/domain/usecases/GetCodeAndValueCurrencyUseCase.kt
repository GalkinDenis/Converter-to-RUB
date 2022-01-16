package ru.denis.convertertorub.domain.usecases

import ru.denis.convertertorub.data.model.toCodeAndValueCurrency
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class GetCodeAndValueCurrencyUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository
) {

    suspend operator fun invoke(targetCurrencyName: String)
    = currenciesRepository.getCodeAndValueCurrency(targetCurrencyName).toCodeAndValueCurrency()

}