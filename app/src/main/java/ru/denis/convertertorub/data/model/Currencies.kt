package ru.denis.convertertorub.data.model

data class Currencies(
    val Date: String?,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute : Map<String, Currency>
)

data class Currency(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    var Nominal: Int,
    val Name: String,
    var Value: Double,
    val Previous: Double
)