package ru.denis.convertertorub.data.datasources.localdatasource

import android.content.Context
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency

interface LocalDataSource {
    suspend fun saveCurrencies(responseBody: Response<Currencies>)
    suspend fun loadAllCurrencies(): Flow<List<CurrencyEntity>>
    fun trimSize(value: Double): String
    fun getPrefixValue(value: Double): String
    suspend fun saveCurrentDate(body: Currencies?)
    suspend fun getSavedDate(): String
    suspend fun getCodeAndValueCurrency(targetCurrencyName: String): CodeAndValueCurrency
    suspend fun isOnline(): Boolean
}