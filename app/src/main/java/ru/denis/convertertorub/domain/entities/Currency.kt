package ru.denis.convertertorub.domain.entities


data class Currency(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    var Nominal: Int,
    val Name: String,
    var Value: Double,
    var Previous: Double
)

