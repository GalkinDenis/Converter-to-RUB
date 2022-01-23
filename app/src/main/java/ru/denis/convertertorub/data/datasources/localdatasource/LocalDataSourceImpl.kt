package ru.denis.convertertorub.data.datasources.localdatasource

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.datasources.database.ItemDao
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasource
import java.math.RoundingMode
import javax.inject.Inject
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable
import ru.denis.convertertorub.data.model.CurrencyEntity

class LocalDataSourceImpl @Inject constructor(
    private val context: Context,
    private var dao: ItemDao,
    private val preferenceDatasource: PreferenceDatasource
) : LocalDataSource {

    override suspend fun loadAllCurrencies() = dao.getCurrencies()

    override suspend fun saveCurrencies(currency: CurrencyEntity) {
        withContext(Dispatchers.IO) {
            if (currency.Nominal > 1) {
                currency.Value /= currency.Nominal
                currency.Previous /= currency.Nominal
            }
            dao.insertCurrencies(
                CurrencyEntityTable(
                    _ID = currency.ID,
                    numCode = currency.NumCode,
                    charCode = currency.CharCode,
                    name = currency.Name,
                    value = trimSize(currency.Value),
                    difference = getPrefixValue(currency.Value - currency.Previous)
                )
            )
        }
    }

    override fun trimSize(value: Double) =
        context.getString(R.string.rub, value.toBigDecimal().setScale(2, RoundingMode.UP))

    override fun getPrefixValue(value: Double): String {
        return when {
            value > 0 -> context.getString(R.string.plus_and_rub, trimSize(value))
            else -> trimSize(value)
        }
    }

    override suspend fun saveCurrentDate(currentDate: String?) {
        withContext(Dispatchers.IO) {
            preferenceDatasource.saveCurrentDate(
                currentDate,
                context.getString(R.string.current_date)
            )
        }
    }

    override suspend fun getSavedDate() =
        withContext(Dispatchers.IO) {
            preferenceDatasource.getSavedDate(context.getString(R.string.current_date))
        }

    override suspend fun getCodeAndValueCurrency(targetCurrencyName: String) =
        dao.getCodeAndValueCurrency(targetCurrencyName)

}


