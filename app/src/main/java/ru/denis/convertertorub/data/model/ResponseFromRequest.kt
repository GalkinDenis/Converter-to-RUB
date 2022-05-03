package ru.denis.convertertorub.data.model

import retrofit2.Response
import ru.denis.convertertorub.domain.entities.DirtyCurrencies
import ru.denis.convertertorub.domain.entities.ErrorType
import ru.denis.convertertorub.domain.entities.ResponseFromRequest

fun Response<CurrenciesEntity>.toDirtyCurrencies(): ResponseFromRequest {
    return if (this.isSuccessful) {
        val body = this.body()
        ResponseFromRequest.Success(
            timeStamp = timeFormat(body?.Timestamp),
            currenciesList = body?.Valute?.map { currencyEntity ->
                currencyEntity.value.toDirtyCurrencies()
            }
        )
    } else {
        val errorType = when (this.code()) {
            403 -> ErrorType.Forbidden
            404 -> ErrorType.NotFound
            else -> ErrorType.Unknown
        }
        ResponseFromRequest.Error(errorType = errorType)
    }
}

fun timeFormat(currentTime: String?): String {
    return currentTime
        ?.split("T")
        ?.get(0)
        ?.split("-")
        ?.reversed()
        .toString()
}

fun CurrencyEntity.toDirtyCurrencies(): DirtyCurrencies {
    return DirtyCurrencies(
        ID = this.ID,
        NumCode = this.NumCode,
        CharCode = this.CharCode,
        Nominal = this.Nominal,
        Name = this.Name,
        Value = this.Value,
        Previous = this.Previous,
    )
}
