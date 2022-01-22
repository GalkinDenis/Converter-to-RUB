package ru.denis.convertertorub.presentation.currenciesfragmentviewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable
import ru.denis.convertertorub.domain.entities.Currency
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.domain.usecases.*
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.baseviewmodels.BaseListOfCurrenciesViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrenciesFragmentViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val saveCurrenciesUseCase: SaveCurrenciesUseCase,
    private val loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase,
    private val getSavedDataUseCase: GetSavedDataUseCase,
    private val savedDateUseCase: SaveDateUseCase,
    private var getNetworkStatusUseCase: GetNetworkStatusUseCase
) : BaseListOfCurrenciesViewModel<List<ReadyCurrencies>>() {

    init {
        viewModelScope.launch {
            loadAllCurrenciesUseCase()
                .catch { errorHandler = ErrorType.GET_ERROR }
                .flowOn(Dispatchers.IO)
                .collect { listOfCurrencies ->
                    if (getNetworkStatusUseCase()) {
                        if (compareDates()) {
                            getCurrencies()
                            return@collect
                        }
                    } else {
                        currentDate = getSavedDataUseCase()
                        if (listOfCurrencies.isEmpty()) errorHandler = ErrorType.LOAD_ERROR
                    }
                    getListOfCurrencies = listOfCurrencies
                }
        }
    }

    private val getCurrenciesExceptionHandler = CoroutineExceptionHandler { _, _ ->
        errorHandler = ErrorType.LOAD_ERROR
    }

    fun getCurrencies() {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
            saveCurrencies(getCurrenciesUseCase())
        }
    }

    private val saveCurrenciesExceptionHandler = CoroutineExceptionHandler { _, _ ->
        errorHandler = ErrorType.INSERT_ERROR
    }

    private fun saveCurrencies(currencies: List<Currency>?) {
        viewModelScope.launch(saveCurrenciesExceptionHandler) {
            saveCurrenciesUseCase(currencies)
        }
    }

    private suspend fun compareDates(): Boolean {
        val current = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val savedDate = getSavedDataUseCase()

        currentDate = current

        return if (current != savedDate) {
            savedDateUseCase(current)
            true
        } else {
            false
        }


        //currentDate = savedDate
        //return savedDate != currentDate
    }

}
