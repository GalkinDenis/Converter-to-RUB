package ru.denis.convertertorub.domain.entities

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
    var Value: Double,
    var Previous: Double
)
