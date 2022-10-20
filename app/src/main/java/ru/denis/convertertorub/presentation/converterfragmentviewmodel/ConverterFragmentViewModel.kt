package ru.denis.convertertorub.presentation.converterfragmentviewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import ru.denis.convertertorub.di.qualifiers.DefaultDispatcher
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.usecases.GetCodeAndValueCurrencyUseCase
import ru.denis.convertertorub.domain.usecases.GetResourcesStringsUseCase
import ru.denis.convertertorub.presentation.baseviewmodels.BaseConverterViewModel
import java.math.RoundingMode
import javax.inject.Inject

const val RUB = " RUB"

@HiltViewModel
class ConverterFragmentViewModel @Inject constructor(
    @DefaultDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val getCodeAndValueCurrencyUseCase: GetCodeAndValueCurrencyUseCase,
    private val getResourcesStringsUseCase: GetResourcesStringsUseCase
) : BaseConverterViewModel<String>() {

    private lateinit var codeAndValueCurrency: CodeAndValueCurrency

    private val converterCurrencyExceptionHandler = CoroutineExceptionHandler { _, error ->
        errorHandler = error
    }

    init {
        changeTypeConverter("")
    }

    fun changeTypeConverter(fieldOfTargetCurrency: String) {
        viewModelScope.launch(converterCurrencyExceptionHandler) {
            convertFromRubles = !convertFromRubles
            when (convertFromRubles) {
                false -> {
                    typeCurrencyInFirstField = getResourcesStringsUseCase().getOtherCurrency()
                    convertToOrFromState = getResourcesStringsUseCase().getConvertFrom()
                }
                true -> {
                    typeCurrencyInFirstField = getResourcesStringsUseCase().getRubCurrency()
                    convertToOrFromState = getResourcesStringsUseCase().getConvertTo()
                }
            }
            when {
                (convertFromRubles) -> suffixText = getResourcesStringsUseCase().getRubles()
                fieldOfTargetCurrency.isBlank() && !convertFromRubles -> suffixText =
                    getResourcesStringsUseCase().getDash()
                fieldOfTargetCurrency.isNotBlank() && !convertFromRubles -> suffixText =
                    codeAndValueCurrency.charCode
            }
        }
    }

        fun getCodeAndValueCurrency(fieldOfTargetCurrency: String) {
            if (fieldOfTargetCurrency.isNotBlank()) {
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
        withContext(coroutineDispatcher) {
            divisionResult =
                (fieldOfRub.toDouble() / charCodeAndValueCurrency.value.toDouble())
                    .toBigDecimal()
                    .setScale(2, RoundingMode.UP)
                    .toString() + " " + charCodeAndValueCurrency.charCode
        }
    }

    private suspend fun convertToRubles(
        fieldOfRub: String,
        charCodeAndValueCurrency: CodeAndValueCurrency
    ) {
        withContext(coroutineDispatcher) {
            divisionResult = (fieldOfRub.toDouble() * charCodeAndValueCurrency.value.toDouble())
                .toBigDecimal()
                .setScale(2, RoundingMode.UP)
                .toString() + " " + RUB
        }
    }
}