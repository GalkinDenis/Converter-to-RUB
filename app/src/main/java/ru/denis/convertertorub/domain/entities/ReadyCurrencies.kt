package ru.denis.convertertorub.domain.entities

import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable

data class ReadyCurrencies(
    var _ID: String,
    var numCode: String,
    var charCode: String,
    var name: String,
    var value: String,
    var difference: String,
)

fun CurrencyEntityTable.toReadyCurrencies(): ReadyCurrencies {
    return ReadyCurrencies(
        _ID = this._ID,
        numCode = this.numCode,
        charCode = this.charCode,
        name = this.name,
        value = this.value,
        difference = this.difference
    )
}