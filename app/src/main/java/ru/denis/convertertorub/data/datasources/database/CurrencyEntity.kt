package ru.denis.convertertorub.data.datasources.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

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
