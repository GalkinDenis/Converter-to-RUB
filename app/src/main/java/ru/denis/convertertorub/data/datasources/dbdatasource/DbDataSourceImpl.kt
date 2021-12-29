package ru.denis.convertertorub.data.datasources.dbdatasource

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.datasources.database.ItemDao
import ru.denis.convertertorub.data.model.Currencies
import java.math.RoundingMode
import javax.inject.Inject

class DbDataSourceImpl @Inject constructor(
    private var dao: ItemDao
) : DbDataSource {

    override suspend fun saveCurrencies(responseBody: Response<Currencies>) {
        withContext(Dispatchers.IO) {
            responseBody.body()?.Valute?.forEach { (_, currency) ->
                if (currency.Nominal > 1) {
                    currency.Value /= currency.Nominal
                    currency.Previous /= currency.Nominal
                    currency.Nominal = 1
                }
                dao.insertCurrencies(
                    CurrencyEntity(
                        _ID = currency.ID,
                        NumCode = currency.NumCode,
                        CharCode = currency.CharCode,
                        Nominal = currency.Nominal,
                        Name = currency.Name,
                        Value = trimSize(currency.Value),
                        Difference = trimSize(currency.Value - currency.Previous)
                    )
                )
            }
        }
    }

    override suspend fun loadAllCurrencies() = dao.getCurrencies()

    override fun trimSize(value: Double) = value.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
}