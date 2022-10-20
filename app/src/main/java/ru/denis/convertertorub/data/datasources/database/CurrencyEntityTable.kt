package ru.denis.convertertorub.data.datasources.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.denis.convertertorub.domain.entities.ReadyCurrencies
import ru.denis.convertertorub.presentation.converterfragmentviewmodel.RUB

@Entity(tableName = "currency_entity")
data class CurrencyEntityTable(

    @ColumnInfo(name = "_ID")
    var _ID: String,

    @ColumnInfo(name = "NumCode")
    var numCode: String,

    @ColumnInfo(name = "CharCode")
    var charCode: String,

    @PrimaryKey
    @ColumnInfo(name = "Name")
    var name: String,

    @ColumnInfo(name = "Value")
    var value: String,

    @ColumnInfo(name = "Difference")
    var difference: String,
)

fun CurrencyEntityTable.toReadyCurrencies(): ReadyCurrencies {
    return ReadyCurrencies(
        _ID = _ID,
        numCode = numCode,
        charCode = charCode,
        name = name,
        value = value + RUB,
        difference = difference + RUB
    )
}

fun ReadyCurrencies.readyCurrenciesToTableCurrencies(): CurrencyEntityTable {
    return CurrencyEntityTable(
        _ID = _ID,
        numCode = numCode,
        charCode = charCode,
        name = name,
        value = value,
        difference = difference
    )
}

