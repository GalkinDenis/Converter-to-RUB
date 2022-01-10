package ru.denis.convertertorub.presentation.converterfragmentviewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.usecases.GetCodeAndValueCurrencyUseCase
import ru.denis.convertertorub.presentation.baseviewmodels.BaseConverterViewModel
import java.math.RoundingMode
import javax.inject.Inject

class ConverterFragmentViewModel @Inject constructor(
    private val getCodeAndValueCurrencyUseCase: GetCodeAndValueCurrencyUseCase
) : BaseConverterViewModel<String>() {

    private val getTargetCurrencyValueExceptionHandler = CoroutineExceptionHandler { _, error ->
        errorHandler = error
    }

    fun convert(fieldOfRub: String, targetCurrencyName: String) {
        viewModelScope.launch(getTargetCurrencyValueExceptionHandler) {
            val codeAndValueCurrency = async { getCodeAndValueCurrencyUseCase(targetCurrencyName) }
            convert(fieldOfRub, codeAndValueCurrency.await())
        }
    }

    private suspend fun convert(
        fieldOfRub: String,
        charCodeAndValueCurrency: CodeAndValueCurrency
    ) {
        withContext(Dispatchers.IO) {
            divisionResult =
                (fieldOfRub.toDouble() / charCodeAndValueCurrency.value.split(" ")[0].toDouble())
                    .toBigDecimal()
                    .setScale(2, RoundingMode.UP)
                    .toString() + " " + charCodeAndValueCurrency.charCode
        }
    }

}