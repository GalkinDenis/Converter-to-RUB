package ru.denis.convertertorub.domain.entities

import ru.denis.convertertorub.util.ErrorType

sealed class ResponseFromRequest {
    data class Success(
        val timeStamp: String,
        val currenciesList: List<DirtyCurrencies>?
    ) : ResponseFromRequest()
    data class Error(val errorType: ErrorType) : ResponseFromRequest()
}

data class DirtyCurrencies(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Int,
    val Name: String,
    val Value: Double,
    val Previous: Double
)
