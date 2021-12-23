package ru.denis.convertertorub.presentation.currenciesfragmentviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.domain.usecases.GetCurrenciesUseCase
import ru.denis.convertertorub.domain.usecases.SaveCurrenciesUseCase
import ru.denis.convertertorub.presentation.SingleLiveEvent
import javax.inject.Inject

class CurrenciesFragmentViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val saveCurrenciesUseCase: SaveCurrenciesUseCase

) : ViewModel() {

    private val _getCurrenciesError = SingleLiveEvent<Throwable>()
    val getCurrenciesError: SingleLiveEvent<Throwable> = _getCurrenciesError

    private val getCurrenciesExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _getCurrenciesError.value = exception
    }

    fun getCurrencies() {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
            val response = async { getCurrenciesUseCase() }

            val responseBody = response.await()
            if (responseBody.isSuccessful) saveCurrencies(responseBody)
        }
    }

    private fun saveCurrencies(responseBody: Response<Currencies>) {
        viewModelScope.launch(getCurrenciesExceptionHandler) {
            saveCurrenciesUseCase(responseBody)
        }
    }
}
