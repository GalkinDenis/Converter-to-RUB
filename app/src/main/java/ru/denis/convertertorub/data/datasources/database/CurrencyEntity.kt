package ru.denis.convertertorub.data.datasources.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "currency_entity")
data class CurrencyEntity(

    @ColumnInfo(name = "_ID")
    val _ID: String,

    @ColumnInfo(name = "NumCode")
    val NumCode: String,

    @ColumnInfo(name = "CharCode")
    val CharCode: String,

    @ColumnInfo(name = "Name")
    val Name: String,

    @PrimaryKey
    @ColumnInfo(name = "Value")
    val Value: String,

    @ColumnInfo(name = "Difference")
    val Difference: String,
)
