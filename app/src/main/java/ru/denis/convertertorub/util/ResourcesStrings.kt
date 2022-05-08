package ru.denis.convertertorub.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.denis.convertertorub.R
import javax.inject.Inject

class ResourcesStrings @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun getOtherCurrency() = context.getString(R.string.other_currency)
    fun getConvertFrom() = context.getString(R.string.convert_from)
    fun getRubCurrency() = context.getString(R.string.rub_currency)
    fun getConvertTo() = context.getString(R.string.convert_to)
    fun getDash() = context.getString(R.string.dash)
    fun getRubles() = context.getString(R.string.rubles)
}