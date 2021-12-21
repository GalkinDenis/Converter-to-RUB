package ru.denis.convertertorub.presentation.currenciesfragmentviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.denis.convertertorub.domain.usecases.GetCurrenciesFromRemoteUseCase
import ru.denis.convertertorub.domain.usecases.SaveCurrenciesToLocalStorageUseCase
import ru.denis.convertertorub.presentation.SingleLiveEvent
import javax.inject.Inject

class CurrenciesFragmentViewModel @Inject constructor(
    private val getCurrenciesFromRemoteUseCase: GetCurrenciesFromRemoteUseCase,
    private val saveCurrenciesToLocalStorageUseCase: SaveCurrenciesToLocalStorageUseCase

) : ViewModel() {

    private val _getCurrenciesError = SingleLiveEvent<Throwable>()
    val getCurrenciesError: SingleLiveEvent<Throwable> = _getCurrenciesError

    private val getCurrenciesExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _getCurrenciesError.value = exception
    }

    suspend fun getCurrenciesFromRemote() {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
            val responseFromRemote = getCurrenciesFromRemoteUseCase()
        }
    }

    suspend fun saveCurrenciesToLocalStorage() {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
        }

    }
}