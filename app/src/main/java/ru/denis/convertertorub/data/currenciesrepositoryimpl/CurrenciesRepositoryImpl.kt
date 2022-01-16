package ru.denis.convertertorub.data.currenciesrepositoryimpl

import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.model.toCurrencies
import ru.denis.convertertorub.data.model.toCurrenciesEntity
import ru.denis.convertertorub.domain.entities.Currency
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val cbRfDataSource: CbRfDataSource,
    private val localDataSource: LocalDataSource
) : CurrenciesRepository {

    override suspend fun loadAllCurrencies() = localDataSource.loadAllCurrencies()

    override suspend fun getCurrencies() =
        cbRfDataSource.getCurrencies().body()?.Valute?.map { currencyEntity ->
            currencyEntity.value.toCurrencies()
            /*
            CurrencyEntity(
                ID = currencyEntity.value.ID,
                NumCode = currencyEntity.value.NumCode,
                CharCode = currencyEntity.value.CharCode,
                Nominal = currencyEntity.value.Nominal,
                Name = currencyEntity.value.Name,
                Value = currencyEntity.value.Value,
                Previous = currencyEntity.value.Previous
            ).toCurrencies()
             */
        }

    override suspend fun saveCurrencies(currencies: List<Currency>?) {
        currencies?.forEach { currency ->
            localDataSource.saveCurrencies(currency.toCurrenciesEntity())
        }

    }

    override suspend fun getSavedDate() = localDataSource.getSavedDate()

    override suspend fun getCodeAndValueCurrency(targetCurrencyName: String) =
        localDataSource.getCodeAndValueCurrency(targetCurrencyName)

    override suspend fun checkOnline() = localDataSource.checkOnline()

}