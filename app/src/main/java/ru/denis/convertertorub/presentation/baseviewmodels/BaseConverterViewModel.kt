package ru.denis.convertertorub.presentation.baseviewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.SingleLiveEvent

abstract class BaseConverterViewModel<A> : ViewModel() {

    private val _divisionResult: MutableStateFlow<A?> = MutableStateFlow(null)
    fun divisionResult(): StateFlow<A?> = _divisionResult

    protected var divisionResult: A
        get() = _divisionResult.value
            ?: throw UninitializedPropertyAccessException()
        set(value) {
            if (_divisionResult.value == value) {
                _divisionResult.value = null
            }
            _divisionResult.value = value
        }

    private val _errorHandler = SingleLiveEvent<Throwable?>()
    fun showError(): SingleLiveEvent<Throwable?> = _errorHandler

    protected var errorHandler: Throwable
        get() = _errorHandler.value
            ?: throw UninitializedPropertyAccessException()
        set(value) {
            if (_errorHandler.value == value) {
                _errorHandler.value = null
            }
            _errorHandler.value = value
        }




















    protected var convertFromRubles: Boolean = true
















    private val _otherCurrencyState: MutableStateFlow<String> = MutableStateFlow("")
    fun otherCurrency(): StateFlow<String> = _otherCurrencyState

    protected var otherCurrencyState: String
        get() = _otherCurrencyState.value
        set(value) {
            if (_otherCurrencyState.value == value) {
                _otherCurrencyState.value = ""
            }
            _otherCurrencyState.value = value
        }

    private val _convertFromState: MutableStateFlow<String> = MutableStateFlow("")
    fun convertFrom(): StateFlow<String> = _convertFromState

    protected var convertFromState: String
        get() = _convertFromState.value
        set(value) {
            if (_convertFromState.value == value) {
                _convertFromState.value = ""
            }
            _convertFromState.value = value
        }

    private val _suffixText: MutableStateFlow<String> = MutableStateFlow("")
    fun suffixText(): StateFlow<String> = _suffixText

    protected var suffixText: String
        get() = _suffixText.value
        set(value) {
            if (_suffixText.value == value) {
                _suffixText.value = ""
            }
            _suffixText.value = value
        }

















}