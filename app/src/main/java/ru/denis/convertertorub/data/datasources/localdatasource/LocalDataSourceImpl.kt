package ru.denis.convertertorub.data.datasources.localdatasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.datasources.database.ItemDao
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasource
import javax.inject.Inject
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: ItemDao,
    private val preferenceDatasource: PreferenceDatasource
) : LocalDataSource {

    override suspend fun loadAllCurrencies() = dao.getCurrencies()

    override suspend fun saveCurrencies(readyCurrencies: List<CurrencyEntityTable>?) {
        readyCurrencies?.forEach { currencies ->
            dao.insertCurrencies(currencies)
        }
    }

    override suspend fun saveCurrentDate(currentDate: String?) {
        preferenceDatasource.saveCurrentDate(
            currentDate,
            context.getString(R.string.current_date)
        )
    }

    override suspend fun getSavedDate() =
        preferenceDatasource.getSavedDate(context.getString(R.string.current_date))

    override suspend fun getCodeAndValueCurrency(targetCurrencyName: String) =
        dao.getCodeAndValueCurrency(targetCurrencyName)

}


