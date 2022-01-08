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
    val numCode: String,

    @ColumnInfo(name = "CharCode")
    val charCode: String,

    @ColumnInfo(name = "Name")
    val name: String,

    @PrimaryKey
    @ColumnInfo(name = "Value")
    val value: String,

    @ColumnInfo(name = "Difference")
    val difference: String,
)
