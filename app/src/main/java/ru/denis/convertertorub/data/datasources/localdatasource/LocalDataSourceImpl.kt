package ru.denis.convertertorub.data.datasources.localdatasource

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.datasources.database.ItemDao
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasource
import ru.denis.convertertorub.data.model.Currencies
import java.math.RoundingMode
import javax.inject.Inject
import android.net.NetworkInfo

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi


class LocalDataSourceImpl @Inject constructor(
    private val context: Context,
    private var dao: ItemDao,
    private val preferenceDatasource: PreferenceDatasource
) : LocalDataSource {

    override suspend fun loadAllCurrencies() = dao.getCurrencies()

    override suspend fun saveCurrencies(responseBody: Response<Currencies>) {
        withContext(Dispatchers.IO) {
            val body = responseBody.body()
            saveCurrentDate(body)
            body?.Valute?.forEach { (_, currency) ->
                if (currency.Nominal > 1) {
                    currency.Value /= currency.Nominal
                    currency.Previous /= currency.Nominal
                }
                dao.insertCurrencies(
                    CurrencyEntity(
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
    }

    override fun trimSize(value: Double) =
        context.getString(R.string.rub, value.toBigDecimal().setScale(2, RoundingMode.UP))

    override fun getPrefixValue(value: Double): String {
        return when {
            value > 0 -> context.getString(R.string.plus_and_rub, trimSize(value))
            else -> trimSize(value)
        }
    }

    override suspend fun saveCurrentDate(body: Currencies?) {
        withContext(Dispatchers.IO) {
            preferenceDatasource.saveCurrentDate(
                body?.Timestamp,
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

    override suspend fun checkOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}


