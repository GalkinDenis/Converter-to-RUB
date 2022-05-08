package ru.denis.convertertorub.data.model

import com.google.gson.annotations.SerializedName

data class CurrenciesEntity(
    @SerializedName("Date") val Date: String?,
    @SerializedName("PreviousDate") val PreviousDate: String,
    @SerializedName("PreviousURL") val PreviousURL: String,
    @SerializedName("Timestamp") val Timestamp: String,
    @SerializedName("Valute") val Valute : Map<String, CurrencyEntity>
)

data class CurrencyEntity(
    @SerializedName("ID") val ID: String,
    @SerializedName("NumCode") val NumCode: String,
    @SerializedName("CharCode") val CharCode: String,
    @SerializedName("Nominal") val Nominal: Int,
    @SerializedName("Name") val Name: String,
    @SerializedName("Value") var Value: Double,
    @SerializedName("Previous") var Previous: Double
)
