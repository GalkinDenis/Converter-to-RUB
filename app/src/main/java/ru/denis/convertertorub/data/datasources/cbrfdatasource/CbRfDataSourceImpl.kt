package ru.denis.convertertorub.data.datasources.cbrfdatasource

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.datasources.network.CbRfApi
import ru.denis.convertertorub.data.model.Currencies
import javax.inject.Inject

class CbRfDataSourceImpl @Inject constructor(
    private val context: Context,
    private val cbRfApi: CbRfApi
) : CbRfDataSource {
    override suspend fun getCurrencies(): Response<Currencies> =
        withContext(Dispatchers.IO) {
            Log.d("BAG", "R.string.end_point.toString() = ${context.getString(R.string.end_point)}")

            cbRfApi.getCurrencies(context.getString(R.string.end_point))

            //cbRfApi.getCurrencies(R.string.end_point.toString())
        }
}