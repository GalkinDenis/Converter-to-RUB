package ru.denis.convertertorub.data.cbrfdatasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.network.CbRfApi
import ru.denis.convertertorub.domain.entity.Currencies
import javax.inject.Inject

class CbRfDataSourceImpl @Inject constructor(
    private val cbRfApi: CbRfApi
) : CbRfDataSource {

    override suspend fun getCurrenciesFromRemote(): Response<Currencies> =
        withContext(Dispatchers.IO) {
            cbRfApi.getCurrencies(R.string.end_point.toString())
        }

}