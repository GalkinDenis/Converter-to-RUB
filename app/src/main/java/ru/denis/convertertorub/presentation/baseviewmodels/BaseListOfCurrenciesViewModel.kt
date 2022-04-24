package ru.denis.convertertorub.presentation.baseviewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.denis.convertertorub.data.model.ErrorType
import ru.denis.convertertorub.presentation.SingleLiveEvent

abstract class BaseListOfCurrenciesViewModel<A> : ViewModel() {

    private val _totalListOfCurrencies: MutableStateFlow<A?> = MutableStateFlow(null)
    fun totalListOfCurrencies(): StateFlow<A?> = _totalListOfCurrencies

    protected var totalListOfCurrencies: A
        get() = _totalListOfCurrencies.value
            ?: throw UninitializedPropertyAccessException()
        set(value) {
            if (_totalListOfCurrencies.value == value) {
                _totalListOfCurrencies.value = null
            }
            _totalListOfCurrencies.value = value
        }

    private val _errorType = SingleLiveEvent<ErrorType?>()
    fun showError(): SingleLiveEvent<ErrorType?> = _errorType

    protected var errorType: ErrorType
        get() = _errorType.value
            ?: throw UninitializedPropertyAccessException()
        set(value) {
            if (_errorType.value == value) {
                _errorType.value = null
            }
            _errorType.value = value
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