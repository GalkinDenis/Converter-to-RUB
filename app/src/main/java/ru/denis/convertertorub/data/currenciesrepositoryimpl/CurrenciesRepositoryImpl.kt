package ru.denis.convertertorub.data.currenciesrepositoryimpl

import retrofit2.Response
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val cbRfDataSource: CbRfDataSource,
    private val localDataSource: LocalDataSource
) : CurrenciesRepository {

    override suspend fun getCurrencies() = cbRfDataSource.getCurrencies()

    override suspend fun saveCurrencies(responseBody: Response<Currencies>) {
        localDataSource.saveCurrencies(responseBody)
    }

    override suspend fun loadAllCurrencies() = localDataSource.loadAllCurrencies()

    override suspend fun getSavedDate() = localDataSource.getSavedDate()

    override suspend fun getCodeAndValueCurrency(targetCurrencyName: String) =
        localDataSource.getCodeAndValueCurrency(targetCurrencyName)

    override suspend fun isOnline() = localDataSource.isOnline()

}