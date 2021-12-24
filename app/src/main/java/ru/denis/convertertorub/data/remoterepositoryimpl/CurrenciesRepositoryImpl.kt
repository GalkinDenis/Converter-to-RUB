package ru.denis.convertertorub.data.remoterepositoryimpl

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.datasources.dbdatasource.DbDataSource
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val cbRfDataSource: CbRfDataSource,
    private val dbDataSource: DbDataSource
) : CurrenciesRepository {

    override suspend fun getCurrencies() = cbRfDataSource.getCurrencies()

    override suspend fun saveCurrencies(responseBody: Response<Currencies>) {
        dbDataSource.saveCurrencies(responseBody)
    }

    override suspend fun loadAllCurrencies() = dbDataSource.loadAllCurrencies()
}