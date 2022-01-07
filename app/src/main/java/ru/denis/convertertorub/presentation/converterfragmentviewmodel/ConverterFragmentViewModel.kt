package ru.denis.convertertorub.presentation.converterfragmentviewmodel

import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import javax.inject.Inject

class ConverterFragmentViewModel @Inject constructor(

) : ViewModel() {

    //Привязка к жизненному циклу ViewModel поля ввода "RUB value".
    private var _inputField: String? = null
    val inputField get() = _inputField

    //Привязка к жизненному циклу ViewModel поля "Result".
    private var _resultOfDevide: String? = null
    val resultOfDevide get() = _resultOfDevide

    //Запрос единичного объекта таблицы базы данных для функции конвертации.
    //fun getItemValue(itemName: String): LiveData<Valute> {
    //    return repository.getItemValue(itemName, dao)
  //  }

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