package ru.denis.convertertorub.data.currenciesrepositoryimpl

import kotlinx.coroutines.flow.map
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.database.readyCurrenciesToTableCurrencies
import ru.denis.convertertorub.data.datasources.database.toReadyCurrencies
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.model.toCodeAndValueCurrency
import ru.denis.convertertorub.data.model.toDirtyCurrencies
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val cbRfDataSource: CbRfDataSource,
    private val localDataSource: LocalDataSource
) : CurrenciesRepository {

    override suspend fun saveCurrencies(readyCurrencies: List<ReadyCurrencies>?) {
        localDataSource.saveCurrencies(
            readyCurrencies?.map { currencies ->
                currencies.readyCurrenciesToTableCurrencies()
            }
        )
    }

    override suspend fun getDirtyCurrencies() =
        cbRfDataSource.getCurrencies().toDirtyCurrencies()

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