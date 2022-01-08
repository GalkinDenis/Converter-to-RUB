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
            ?: throw UninitializedPropertyAccessException(
                "Was queried before being initialized"
            )
        set(value) {
            if (_divisionResult.value == value) {
                _divisionResult.value = null
            }
            _divisionResult.value = value
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

}