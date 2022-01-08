package ru.denis.convertertorub.domain.entities

import androidx.room.ColumnInfo

data class CodeAndValueCurrency(
    @ColumnInfo(name = "CharCode") val charCode: String,
    @ColumnInfo(name = "Value") val value: String
)
