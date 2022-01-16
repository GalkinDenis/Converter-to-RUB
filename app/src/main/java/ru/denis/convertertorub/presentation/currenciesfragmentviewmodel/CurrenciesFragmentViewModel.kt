package ru.denis.convertertorub.presentation.currenciesfragmentviewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.domain.entities.Currencies
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
    private var getNetworkStatusUseCase: GetNetworkStatusUseCase
) : BaseListOfCurrenciesViewModel<List<CurrencyEntity>>() {

    init {
        viewModelScope.launch {
            loadAllCurrenciesUseCase()
                .catch { errorHandler = ErrorType.GET_ERROR }
                .flowOn(Dispatchers.IO)
                .collect { listOfCurrencies ->
                    if (compareDates()) {
                        if (getNetworkStatusUseCase()) {
                            getCurrencies()
                            return@collect
                        } else {
                            errorHandler = ErrorType.LOAD_ERROR
                        }
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
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val savedDate = getSavedDataUseCase().split("T")[0]
        currentDate = savedDate
        return savedDate != date
    }

}
