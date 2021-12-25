package ru.denis.convertertorub.data.datasources.dbdatasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.datasources.database.ItemDao
import ru.denis.convertertorub.data.model.Currencies
import javax.inject.Inject

class DbDataSourceImpl @Inject constructor(
    private var dao: ItemDao
) : DbDataSource {

    override suspend fun saveCurrencies(responseBody: Response<Currencies>) {
        withContext(Dispatchers.IO) {
            responseBody.body()?.Valute?.forEach { (_, currency) ->
                if (currency.Nominal > 1) currency.Value /= currency.Nominal
                dao.update(
                    CurrencyEntity(
                        _ID = currency.ID,
                        NumCode = currency.NumCode,
                        CharCode = currency.CharCode,
                        Nominal = currency.Nominal,
                        Name = currency.Name,
                        Value = currency.Value,
                        Previous = currency.Previous
                    )
                )
            }
        }
    }

    override suspend fun loadAllCurrencies() = dao.getCurrencies().flowOn(Dispatchers.IO)
}