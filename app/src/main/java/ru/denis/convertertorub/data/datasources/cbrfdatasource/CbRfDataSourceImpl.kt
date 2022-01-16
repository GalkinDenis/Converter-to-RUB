package ru.denis.convertertorub.data.datasources.cbrfdatasource

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.datasources.network.CbRfApi
import ru.denis.convertertorub.data.model.CurrenciesEntity
import javax.inject.Inject

class CbRfDataSourceImpl @Inject constructor(
    private val context: Context,
    private val cbRfApi: CbRfApi
) : CbRfDataSource {

    override suspend fun getCurrencies(): Response<CurrenciesEntity> =
        withContext(Dispatchers.IO) {
            cbRfApi.getCurrencies(context.getString(R.string.end_point))
        }

}