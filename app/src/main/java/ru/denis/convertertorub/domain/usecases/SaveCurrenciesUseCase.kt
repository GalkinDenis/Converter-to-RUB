package ru.denis.convertertorub.domain.usecases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.R
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.entities.DirtyCurrencies
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import ru.denis.convertertorub.domain.usecases.baseusecases.InSharedUseCase
import java.math.RoundingMode
import javax.inject.Inject

@ViewModelScoped
class SaveCurrenciesUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val currenciesRepository: CurrenciesRepository
) : InSharedUseCase<List<DirtyCurrencies>?>(dispatcher) {

    override suspend fun execute(inParameter: List<DirtyCurrencies>?) {
        currenciesRepository.saveCurrencies(
            inParameter?.map { dirtyCurrencies ->
                formatCurrencies(dirtyCurrencies)
            }
        )
    }

    private fun formatCurrencies(currency: DirtyCurrencies): ReadyCurrencies {
        var valueValue = currency.Value
        var valuePrevious = currency.Previous
        val valueNominal = currency.Nominal

        if (valueNominal > 1) {
            valueValue /= valueNominal
            valuePrevious /= valueNominal
        }
        return ReadyCurrencies(
            _ID = currency.ID,
            numCode = currency.NumCode,
            charCode = currency.CharCode,
            name = currency.Name,
            value = trimSize(valueValue),
            difference = getPrefixValue(valueValue - valuePrevious)
        )
    }

    private fun getPrefixValue(value: Double): String {
        return when {
            value > 0 -> context.getString(R.string.plus_and_rub, trimSize(value))
            else -> trimSize(value)
        }
    }

    private fun trimSize(value: Double) =
        value
            .toBigDecimal()
            .setScale(2, RoundingMode.UP).toString()
}