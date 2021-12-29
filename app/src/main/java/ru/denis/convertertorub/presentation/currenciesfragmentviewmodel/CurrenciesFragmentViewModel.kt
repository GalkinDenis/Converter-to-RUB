package ru.denis.convertertorub.presentation.currenciesfragmentviewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.data.model.Currencies
import ru.denis.convertertorub.domain.usecases.GetCurrenciesUseCase
import ru.denis.convertertorub.domain.usecases.LoadAllCurrenciesUseCase
import ru.denis.convertertorub.domain.usecases.SaveCurrenciesUseCase
import ru.denis.convertertorub.presentation.BaseFlowViewModel
import ru.denis.convertertorub.presentation.SingleLiveEvent
import javax.inject.Inject

class CurrenciesFragmentViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val saveCurrenciesUseCase: SaveCurrenciesUseCase,
    private val loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase
) : BaseFlowViewModel<List<CurrencyEntity>>() {

    private val _loadCurrenciesError = SingleLiveEvent<Throwable>()
    val loadCurrenciesError: SingleLiveEvent<Throwable> = _loadCurrenciesError

    init {
        viewModelScope.launch {
            loadAllCurrenciesUseCase()
                .catch { error -> _loadCurrenciesError.value = error }
                .flowOn(Dispatchers.IO)
                .collect { listOfCurrencies -> viewAction = listOfCurrencies }
        }
    }

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

    private val _saveCurrenciesError = SingleLiveEvent<Throwable>()
    val saveCurrenciesError: SingleLiveEvent<Throwable> = _saveCurrenciesError

    private val saveCurrenciesExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _saveCurrenciesError.value = exception
    }

    private fun saveCurrencies(responseBody: Response<Currencies>) {
        viewModelScope.launch(saveCurrenciesExceptionHandler) {
            saveCurrenciesUseCase(responseBody)
        }
    }
}
