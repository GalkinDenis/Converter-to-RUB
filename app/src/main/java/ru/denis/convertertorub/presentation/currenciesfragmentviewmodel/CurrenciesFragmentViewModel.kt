package ru.denis.convertertorub.presentation.currenciesfragmentviewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.denis.convertertorub.domain.entities.ErrorType
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.domain.entities.ResponseFromRequest
import ru.denis.convertertorub.domain.usecases.*
import ru.denis.convertertorub.presentation.baseviewmodels.BaseListOfCurrenciesViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesFragmentViewModel @Inject constructor(
    private val getNetWorkStatusUseCase: GetNetWorkStatusUseCase,
    private val getDirtyCurrenciesUseCase: GetDirtyCurrenciesUseCase,
    private val loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase,
    private val getSavedDataUseCase: GetSavedDataUseCase,
    private val saveDateUseCase: SaveDateUseCase,
    private val saveCurrenciesUseCase: SaveCurrenciesUseCase,
) : BaseListOfCurrenciesViewModel<List<ReadyCurrencies>>() {

    private val getCurrenciesExceptionHandler = CoroutineExceptionHandler { context, exception ->
        Log.e(
            "CurrenciesException",
            "getCurrenciesExceptionHandler: $context: $exception"
        )
    }

    init {
        loadCurrencies()
        getDirtyCurrencies()
    }

    private fun loadCurrencies() {
        viewModelScope.launch {
            loadAllCurrenciesUseCase()
                .catch { errorType = ErrorType.NoBDConnection }
                .collect { listOfCurrencies ->
                    totalListOfCurrencies = listOfCurrencies
                }
        }
    }

    private suspend fun checkNetWorkStatus() =
        if (getNetWorkStatusUseCase()) {
            true
        } else {
            errorType = ErrorType.NoInternetConnection
            false
        }

    private fun getDirtyCurrencies() {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
            if (!checkNetWorkStatus()) return@launch
            when (val dirtyCurrencies = getDirtyCurrenciesUseCase()) {
                is ResponseFromRequest.Success -> {
                    val date = dirtyCurrencies.timeStamp
                    when (date != getSavedDataUseCase()) {
                        true -> {
                            saveDateUseCase(date)
                            currentDate = date
                            saveCurrenciesUseCase(dirtyCurrencies.currenciesList)
                        }
                        false -> return@launch
                    }
                }
                is ResponseFromRequest.Error -> {
                    errorType = dirtyCurrencies.errorType
                }
            }
        }
    }

    fun getCurrencies() {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
            getDirtyCurrencies()
        }
    }

}
