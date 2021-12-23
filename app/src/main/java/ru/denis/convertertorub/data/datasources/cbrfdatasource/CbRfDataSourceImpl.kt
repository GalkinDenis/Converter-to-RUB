package ru.denis.convertertorub.data.datasources.cbrfdatasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.datasources.network.CbRfApi
import ru.denis.convertertorub.domain.entity.Currencies
import javax.inject.Inject

class CbRfDataSourceImpl @Inject constructor(
    private val cbRfApi: CbRfApi
) : CbRfDataSource {

    override suspend fun getCurrencies(): Response<Currencies> =
        withContext(Dispatchers.IO) {
            cbRfApi.getCurrencies(R.string.end_point.toString())
        }

}