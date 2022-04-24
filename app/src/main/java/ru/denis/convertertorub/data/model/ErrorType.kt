package ru.denis.convertertorub.data.model

import android.content.Context
import ru.denis.convertertorub.R

enum class ErrorType {
    Unauthorized,
    Forbidden,
    NotFound,
    NoInternetConnection,
    BadInternetConnection,
    SocketTimeOut,
    Unknown
}

fun ErrorType.getMessage(context: Context): String {
    return when(this) {
        ErrorType.Unauthorized -> {
            context.getString(R.string.unauthorized)
        }
        ErrorType.Forbidden -> {
            context.getString(R.string.forbidden)
        }
        ErrorType.NotFound -> {
            context.getString(R.string.notFound)
        }
        ErrorType.NoInternetConnection -> {
            context.getString(R.string.no_connection)
        }
        ErrorType.BadInternetConnection -> {
            context.getString(R.string.bad_connection)
        }
        ErrorType.SocketTimeOut -> {
            context.getString(R.string.time_out)
        }
        ErrorType.Unknown -> {
            context.getString(R.string.unknown)
        }
    }
}