package ru.denis.convertertorub.data.model

data class CurrenciesEntity(
    val Date: String?,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute : Map<String, CurrencyEntity>
)

data class CurrencyEntity(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    var Nominal: Int,
    val Name: String,
    var Value: Double,
    var Previous: Double
)
