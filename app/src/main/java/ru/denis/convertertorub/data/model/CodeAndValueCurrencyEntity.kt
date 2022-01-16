package ru.denis.convertertorub.data.model

import androidx.room.ColumnInfo
import ru.denis.convertertorub.domain.entities.CodeAndValueCurrency

data class CodeAndValueCurrencyEntity(
    @ColumnInfo(name = "CharCode") val charCode: String,
    @ColumnInfo(name = "Value") val value: String
)

fun CodeAndValueCurrencyEntity.toCodeAndValueCurrency(): CodeAndValueCurrency {
    return CodeAndValueCurrency(
        charCode = this.charCode,
        value = this.value,
    )
}


