package ru.denis.convertertorub.presentation.converterfragmentviewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.denis.convertertorub.R
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency
import ru.denis.convertertorub.domain.usecases.GetCodeAndValueCurrencyUseCase
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.baseviewmodels.BaseConverterViewModel
import java.math.RoundingMode
import javax.inject.Inject

class ConverterFragmentViewModel @Inject constructor(
    private val getCodeAndValueCurrencyUseCase: GetCodeAndValueCurrencyUseCase,
) : BaseConverterViewModel<String>() {

    val inputField = R.array.currencies_name_list

    /*
    //Привязка к жизненному циклу ViewModel поля ввода "RUB value".
    private var _inputField: String? = null
    val inputField get() = _inputField

    //Привязка к жизненному циклу ViewModel поля "Result".
    private var _resultOfDevide: String? = null
    val resultOfDevide get() = _resultOfDevide

    //fun getItemValue(itemName: String): LiveData<Valute> {
    //    return repository.getItemValue(itemName, dao)
   //}

     */

    private val getTargetCurrencyValueExceptionHandler = CoroutineExceptionHandler { _, error ->
        Log.d("TAG", "TEST_3 = $error")
    }

    fun convert(fieldOfRub: String, targetCurrencyName: String) {
        viewModelScope.launch(getTargetCurrencyValueExceptionHandler) {
            val codeAndValueCurrency = async { getCodeAndValueCurrencyUseCase(targetCurrencyName) }
            convert(fieldOfRub, codeAndValueCurrency.await())
        }
    }

    private suspend fun convert(
        fieldOfRub: String,
        codeAndValueCurrency: CodeAndValueCurrency
    ) {
        withContext(Dispatchers.IO) {
            divisionResult =
                (fieldOfRub.toDouble() / codeAndValueCurrency.value.split(" ")[0].toDouble())
                    .toBigDecimal()
                    .setScale(2, RoundingMode.UP)
                    .toString() + " " + codeAndValueCurrency.charCode
        }
    }

    /*
    //Функция конвертации валюты.
    fun toDevide(fieldOfRubIsEmpty: String, itemTableValue: Valute){
        _inputField = fieldOfRubIsEmpty
        _resultOfDevide = String.format(
            "%s",
            (fieldOfRubIsEmpty
                .toDouble() / itemTableValue.Value
                    )
                .toBigDecimal()
                .setScale(2, RoundingMode.UP)
                .toString()
        )
    }

     */
}