package ru.denis.convertertorub.domain.usecases

import retrofit2.Response
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class SaveCurrenciesUseCase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository
) {
    suspend operator fun invoke(responseBody: Response<Currencies>) {
        currenciesRepository.saveCurrencies(responseBody)
    }
}