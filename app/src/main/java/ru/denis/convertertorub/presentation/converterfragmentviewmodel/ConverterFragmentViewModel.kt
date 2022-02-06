package ru.denis.convertertorub.presentation.converterfragmentviewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.usecases.GetCodeAndValueCurrencyUseCase
import ru.denis.convertertorub.domain.usecases.GetResourcesStringsUseCase
import ru.denis.convertertorub.presentation.baseviewmodels.BaseConverterViewModel
import java.math.RoundingMode
import javax.inject.Inject

class ConverterFragmentViewModel @Inject constructor(
    private val getCodeAndValueCurrencyUseCase: GetCodeAndValueCurrencyUseCase,
    private val getResourcesStringsUseCase: GetResourcesStringsUseCase
) : BaseConverterViewModel<String>() {

    init {
        changeTypeConverter("")
    }

    private lateinit var codeAndValueCurrency: CodeAndValueCurrency

    private val converterCurrencyExceptionHandler = CoroutineExceptionHandler { _, error ->
        errorHandler = error
    }

    fun changeTypeConverter(fieldOfTargetCurrency: String) {
        convertFromRubles = !convertFromRubles
        when (convertFromRubles) {
            false -> {
                aTypeCurrencyInFirstField = getResourcesStringsUseCase().getOtherCurrency()
                convertToOrFromState = getResourcesStringsUseCase().getConvertFrom()
            }
            true -> {
                aTypeCurrencyInFirstField = getResourcesStringsUseCase().getRubCurrency()
                convertToOrFromState = getResourcesStringsUseCase().getConvertTo()
            }
        }
        when {
            (convertFromRubles) -> suffixText = getResourcesStringsUseCase().getRubles()
            fieldOfTargetCurrency.isBlank() && !convertFromRubles -> suffixText = getResourcesStringsUseCase().getDash()
            fieldOfTargetCurrency.isNotBlank() && !convertFromRubles -> suffixText = codeAndValueCurrency.charCode
        }
    }

    fun getCodeAndValueCurrency(fieldOfTargetCurrency: String) {
        if(fieldOfTargetCurrency.isNotBlank()) {
            viewModelScope.launch(converterCurrencyExceptionHandler) {
                codeAndValueCurrency = getCodeAndValueCurrencyUseCase(fieldOfTargetCurrency)
                if (!convertFromRubles) suffixText = codeAndValueCurrency.charCode
            }
        }
    }

    fun convert(fieldOfValue: String) {
        viewModelScope.launch(converterCurrencyExceptionHandler) {
            when (convertFromRubles) {
                true -> convertFromRubles(fieldOfValue, codeAndValueCurrency)
                false -> convertToRubles(fieldOfValue, codeAndValueCurrency)
            }
        }
    }

    private suspend fun convertFromRubles(
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

    private suspend fun convertToRubles(
        fieldOfRub: String,
        codeAndValueCurrency: CodeAndValueCurrency
    ) {
        withContext(Dispatchers.IO) {
            val valueWithType = codeAndValueCurrency.value.split(" ")
            divisionResult = (fieldOfRub.toDouble() * valueWithType[0].toDouble())
                .toBigDecimal()
                .setScale(2, RoundingMode.UP)
                .toString() + " " + valueWithType[1]
        }
    }

}