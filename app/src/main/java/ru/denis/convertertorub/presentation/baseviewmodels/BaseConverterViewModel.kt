package ru.denis.convertertorub.presentation.baseviewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.denis.convertertorub.utils.SingleLiveEvent

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

    protected var convertFromRubles: Boolean = false

    private val _aTypeCurrencyInFirstField: MutableStateFlow<String> = MutableStateFlow("")
    fun aTypeCurrencyInFirstField(): StateFlow<String> = _aTypeCurrencyInFirstField
    protected var aTypeCurrencyInFirstField: String
        get() = _aTypeCurrencyInFirstField.value
        set(value) {
            if (_aTypeCurrencyInFirstField.value == value) {
                _aTypeCurrencyInFirstField.value = ""
            }
            _aTypeCurrencyInFirstField.value = value
        }

    private val _convertToOrFromState: MutableStateFlow<String> = MutableStateFlow("")
    fun convertToOrFromState(): StateFlow<String> = _convertToOrFromState
    protected var convertToOrFromState: String
        get() = _convertToOrFromState.value
        set(value) {
            if (_convertToOrFromState.value == value) {
                _convertToOrFromState.value = ""
            }
            _convertToOrFromState.value = value
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