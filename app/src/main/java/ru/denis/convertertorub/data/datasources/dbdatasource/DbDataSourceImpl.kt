package ru.denis.convertertorub.data.datasources.dbdatasource

import android.util.Log
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
            Log.d(
                "BAG",
                "responseBody.body()?.Valute?.size =" +
                        " ${responseBody.body()?.Valute?.size}"
            )

            responseBody.body()?.Valute?.size
            responseBody.body()?.Valute?.forEach { (_, currency) ->
                if (currency.Nominal > 1) {
                    currency.Value /= currency.Nominal
                    currency.Previous /= currency.Nominal
                    currency.Nominal = 1
                }
                dao.insert(
                    CurrencyEntity(
                        _ID = currency.ID,
                        NumCode = currency.NumCode,
                        CharCode = currency.CharCode,
                        Nominal = currency.Nominal,
                        Name = currency.Name,
                        Value = currency.Value,
                        Difference = currency.Value - currency.Previous
                    )
                )
            }
        }
    }

    override suspend fun loadAllCurrencies() = dao.getCurrencies().flowOn(Dispatchers.IO)
}