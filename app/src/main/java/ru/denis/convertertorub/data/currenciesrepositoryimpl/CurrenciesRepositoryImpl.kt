package ru.denis.convertertorub.data.currenciesrepositoryimpl

import android.util.Log
import kotlinx.coroutines.flow.map
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.database.toReadyCurrencies
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.model.toCodeAndValueCurrency
import ru.denis.convertertorub.data.model.toCurrencies
import ru.denis.convertertorub.data.model.toCurrenciesEntity
import ru.denis.convertertorub.domain.entities.Currency
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val cbRfDataSource: CbRfDataSource,
    private val localDataSource: LocalDataSource
) : CurrenciesRepository {

    override suspend fun loadAllCurrencies() =
        localDataSource.loadAllCurrencies().map { listCurrencies ->
            Log.d("TAG", "$listCurrencies")
            listCurrencies.map { currency ->
                Log.d("TAG", "$listCurrencies")
                currency.toReadyCurrencies() }
        }

    override suspend fun saveCurrentDate(currentDate: String?) {
        localDataSource.saveCurrentDate(currentDate)
    }

    override suspend fun getCurrencies(): List<Currency>? {
        //val responseFromCbrfApi = cbRfDataSource.getCurrencies().body()
        //saveCurrentDate(responseFromCbrfApi?.Timestamp)
        return cbRfDataSource.getCurrencies().body()?.Valute?.map { currencyEntity ->
            currencyEntity.value.toCurrencies()
        }
    }

    override suspend fun saveCurrencies(currencies: List<Currency>?) {
        currencies?.forEach { currency ->
            localDataSource.saveCurrencies(currency.toCurrenciesEntity())
        }
    }

    override suspend fun getSavedDate() = localDataSource.getSavedDate()

    override suspend fun getCodeAndValueCurrency(targetCurrencyName: String) =
        localDataSource.getCodeAndValueCurrency(targetCurrencyName).toCodeAndValueCurrency()

    override suspend fun checkOnline() = localDataSource.checkOnline()

}