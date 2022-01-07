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
import ru.denis.convertertorub.domain.usecases.GetSavedDataUseCase
import ru.denis.convertertorub.domain.usecases.LoadAllCurrenciesUseCase
import ru.denis.convertertorub.domain.usecases.SaveCurrenciesUseCase
import ru.denis.convertertorub.presentation.BaseFlowViewModel
import ru.denis.convertertorub.presentation.ErrorType
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrenciesFragmentViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val saveCurrenciesUseCase: SaveCurrenciesUseCase,
    private val loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase,
    private val getSavedDataUseCase: GetSavedDataUseCase
) : BaseFlowViewModel<List<CurrencyEntity>>() {

    init {
        viewModelScope.launch {
            loadAllCurrenciesUseCase()
                .catch { errorHandler = ErrorType.GET_ERROR }
                .flowOn(Dispatchers.IO)
                .collect { listOfCurrencies ->
                    if (compareDates()) {
                        getCurrencies()
                        return@collect
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
            val response = async { getCurrenciesUseCase() }
            val responseBody = response.await()
            if (responseBody.isSuccessful) saveCurrencies(responseBody)
        }
    }

    private val saveCurrenciesExceptionHandler = CoroutineExceptionHandler { _, _ ->
        errorHandler = ErrorType.INSERT_ERROR
    }

    private fun saveCurrencies(responseBody: Response<Currencies>) {
        viewModelScope.launch(saveCurrenciesExceptionHandler) {
            saveCurrenciesUseCase(responseBody)
        }
    }

    private suspend fun compareDates(): Boolean {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val savedDate = getSavedDataUseCase().split("T")[0]
        currentDate = savedDate
        return savedDate != date
    }
}
