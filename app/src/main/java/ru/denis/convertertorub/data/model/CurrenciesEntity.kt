package ru.denis.convertertorub.data.model

import ru.denis.convertertorub.domain.entities.Currency


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


fun CurrencyEntity.toCurrencies(): Currency {
    return Currency(
        ID = this.ID,
        NumCode = this.NumCode,
        CharCode = this.CharCode,
        Nominal = this.Nominal,
        Name = this.Name,
        Value = this.Value,
        Previous = this.Previous
    )
}

fun Currency.toCurrenciesEntity(): CurrencyEntity {
    return CurrencyEntity(
        ID = this.ID,
        NumCode = this.NumCode,
        CharCode = this.CharCode,
        Nominal = this.Nominal,
        Name = this.Name,
        Value = this.Value,
        Previous = this.Previous
    )
}