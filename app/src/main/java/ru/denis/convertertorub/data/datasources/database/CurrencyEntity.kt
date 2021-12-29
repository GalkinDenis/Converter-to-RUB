package ru.denis.convertertorub.data.datasources.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "currency_entity")
data class CurrencyEntity(

    @PrimaryKey
    @ColumnInfo(name = "_ID")
    val _ID: String,

    @ColumnInfo(name = "NumCode")
    val NumCode: String,

    @ColumnInfo(name = "CharCode")
    val CharCode: String,

    @ColumnInfo(name = "Nominal")
    val Nominal: Int,

    @ColumnInfo(name = "Name")
    val Name: String,

    @ColumnInfo(name = "Value")
    val Value: Double,

    @ColumnInfo(name = "Difference")
    val Difference: Double,
)