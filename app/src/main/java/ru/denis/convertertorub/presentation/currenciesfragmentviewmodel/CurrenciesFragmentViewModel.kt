package ru.denis.convertertorub.presentation.currenciesfragmentviewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.domain.usecases.*
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.baseviewmodels.BaseListOfCurrenciesViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesFragmentViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase,
    private val getSavedDataUseCase: GetSavedDataUseCase,
    private var getNetworkStatusUseCase: GetNetworkStatusUseCase
) : BaseListOfCurrenciesViewModel<List<ReadyCurrencies>>() {

    init {
        viewModelScope.launch {
            loadAllCurrenciesUseCase()
                .onEach {
                    getCurrencies()
                    currentDate = getSavedDataUseCase()
                }
                .catch { errorHandler = ErrorType.GET_ERROR }
                .flowOn(Dispatchers.IO)
                .collect { listOfCurrencies ->
                    if (!getNetworkStatusUseCase()) errorHandler = ErrorType.LOAD_ERROR
                    getListOfCurrencies = listOfCurrencies
                }
        }
    }

    private val getCurrenciesExceptionHandler = CoroutineExceptionHandler { _, _ ->
        errorHandler = ErrorType.LOAD_ERROR
    }

    fun getCurrencies() {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
            getCurrenciesUseCase()
        }
    }

}
