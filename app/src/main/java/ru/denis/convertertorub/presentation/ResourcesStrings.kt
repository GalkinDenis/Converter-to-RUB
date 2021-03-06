package ru.denis.convertertorub.presentation

import android.content.Context
import ru.denis.convertertorub.R
import javax.inject.Inject

class ResourcesStrings @Inject constructor(
    private val context: Context
) {
    fun getOtherCurrency() = context.getString(R.string.other_currency)
    fun getConvertFrom() = context.getString(R.string.convert_from)
    fun getRubCurrency() = context.getString(R.string.rub_currency)
    fun getConvertTo() = context.getString(R.string.convert_to)
    fun getDash() = context.getString(R.string.dash)
    fun getRubles() = context.getString(R.string.rubles)
}