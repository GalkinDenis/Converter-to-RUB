package ru.denis.convertertorub.presentation.baseviewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.SingleLiveEvent

abstract class BaseListOfCurrenciesViewModel<A> : ViewModel() {

    private val _getListOfCurrencies: MutableStateFlow<A?> = MutableStateFlow(null)
    fun getListOfCurrencies(): StateFlow<A?> = _getListOfCurrencies

    protected var getListOfCurrencies: A
        get() = _getListOfCurrencies.value
            ?: throw UninitializedPropertyAccessException(
                "Was queried before being initialized"
            )
        set(value) {
            if (_getListOfCurrencies.value == value) {
                _getListOfCurrencies.value = null
            }
            _getListOfCurrencies.value = value
        }

    private val _errorHandler = SingleLiveEvent<ErrorType?>()
    fun showError(): SingleLiveEvent<ErrorType?> = _errorHandler

    protected var errorHandler: ErrorType
        get() = _errorHandler.value
            ?: throw UninitializedPropertyAccessException(
                "Was queried before being initialized"
            )
        set(value) {
            if (_errorHandler.value == value) {
                _errorHandler.value = null
            }
            _errorHandler.value = value
        }

    private val _currentDate: MutableStateFlow<String?> = MutableStateFlow("")
    fun showDate(): MutableStateFlow<String?> = _currentDate

    protected var currentDate: String
        get() = _currentDate.value
            ?: ""
        set(value) {
            if (_currentDate.value == value) {
                _currentDate.value = ""
            }
            _currentDate.value = value
        }

}