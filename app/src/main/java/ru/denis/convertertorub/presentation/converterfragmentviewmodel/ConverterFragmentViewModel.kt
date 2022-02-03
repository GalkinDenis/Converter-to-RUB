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

    private lateinit var codeAndValueCurrency: CodeAndValueCurrency

    fun changeCurrencyToConvert(
        otherCurrency: String,
        convertFrom: String,
        fieldOfTargetValute: String
    ) {
        convertFromRubles = !convertFromRubles
        otherCurrencyState = otherCurrency
        convertFromState = convertFrom
        if(fieldOfTargetValute.isNotBlank()) {
            getCodeAndValueCurrency(fieldOfTargetValute)
        }else {
            suffixText = "-"
        }
    }

    fun getCodeAndValueCurrency(fieldOfTargetValute: String) {
        viewModelScope.launch(getTargetCurrencyValueExceptionHandler) {
            codeAndValueCurrency = getCodeAndValueCurrencyUseCase(fieldOfTargetValute)
            suffixText = codeAndValueCurrency.charCode
        }
    }










    private val getTargetCurrencyValueExceptionHandler = CoroutineExceptionHandler { _, error ->
        errorHandler = error
    }










    fun convert(fieldOfRub: String, targetCurrencyName: String) {
        when(convertFromRubles) {
            true -> {
                viewModelScope.launch(getTargetCurrencyValueExceptionHandler) {
                    val codeAndValueCurrency = async { getCodeAndValueCurrencyUseCase(targetCurrencyName) }
                    convertFromRubles(fieldOfRub, codeAndValueCurrency.await())
                }
            }
            false -> {
                convertToRubles(fieldOfRub, codeAndValueCurrency)
            }
        }
    }


    private fun convertToRubles(fieldOfRub: String, codeAndValueCurrency: CodeAndValueCurrency) {
        val endPoint = codeAndValueCurrency.value.split(" ")
        divisionResult = (fieldOfRub.toDouble() * endPoint[0].toDouble())
            .toBigDecimal()
            .setScale(2, RoundingMode.UP)
            .toString() + endPoint[1]
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

}