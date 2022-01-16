package ru.denis.convertertorub.domain.usecases

import ru.denis.convertertorub.domain.entities.Currency
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class SaveCurrenciesUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository
) {
    suspend operator fun invoke(currencies: List<Currency>?) {
        currenciesRepository.saveCurrencies(currencies)
    }
}