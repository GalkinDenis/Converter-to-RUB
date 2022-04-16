package ru.denis.convertertorub.data.currenciesrepositoryimpl

import kotlinx.coroutines.flow.map
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.database.toReadyCurrencies
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.model.toCodeAndValueCurrency
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val cbRfDataSource: CbRfDataSource,
    private val localDataSource: LocalDataSource
) : CurrenciesRepository {

    override suspend fun getCurrencies() {
        val responseFromCbRfApi = cbRfDataSource.getCurrencies().body()
        val currentDate = responseFromCbRfApi?.Timestamp?.split("T")?.get(0)
        if (currentDate != getSavedDate()) {
            saveCurrentDate(currentDate)
            cbRfDataSource.getCurrencies().body()?.Valute?.forEach { currencyEntity ->
                localDataSource.saveCurrencies(currencyEntity.value)
            }
        }
    }

    override suspend fun loadAllCurrencies() =
        localDataSource.loadAllCurrencies().map { listCurrencies ->
            listCurrencies.map { currency ->
                currency.toReadyCurrencies()
            }
        }

    override suspend fun getCodeAndValueCurrency(targetCurrencyName: String) =
        localDataSource.getCodeAndValueCurrency(targetCurrencyName).toCodeAndValueCurrency()

    override suspend fun saveCurrentDate(currentDate: String?) {
        localDataSource.saveCurrentDate(currentDate)
    }

    override suspend fun getSavedDate() = localDataSource.getSavedDate()

}