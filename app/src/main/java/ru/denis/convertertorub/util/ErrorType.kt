package ru.denis.convertertorub.util

import android.content.Context
import ru.denis.convertertorub.R

enum class ErrorType {
    NoInternetConnection,
    NoBDConnection,
    Forbidden,
    NotFound,
    Unknown
}

fun ErrorType.getMessage(context: Context): String {
    return when(this) {
        ErrorType.NoInternetConnection -> {
            context.getString(R.string.load_currencies_error)
        }
        ErrorType.NoBDConnection -> {
            context.getString(R.string.no_bd_connection)
        }
        ErrorType.Forbidden -> {
            context.getString(R.string.forbidden)
        }
        ErrorType.NotFound -> {
            context.getString(R.string.not_found)
        }
        ErrorType.Unknown -> {
            context.getString(R.string.unknown)
        }
    }
}