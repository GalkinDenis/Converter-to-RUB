package ru.denis.convertertorub.data.datasources.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrencyTable")
data class CurrencyEntityDataBase(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

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

    @ColumnInfo(name = "Previous")
    val Previous: Double,
)
