package ru.denis.convertertorub.data.datasources.dbdatasource

import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityDataBase
import ru.denis.convertertorub.data.datasources.database.ItemDao
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.data.model.Currency
import javax.inject.Inject

class DbDataSourceImpl @Inject constructor(
    private var dao: ItemDao
) : DbDataSource {

    override suspend fun saveCurrencies(responseBody: Response<Currencies>) {
        responseBody.body()?.Valute?.forEach { (_, currency) ->
            dao.update(
                CurrencyEntityDataBase(
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