package ru.denis.convertertorub.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import ru.denis.convertertorub.domain.repository.CurrenciesRepository

class GetSavedDataUseCaseTest {

    private val currenciesRepository = mock<CurrenciesRepository>()

    @Test
    fun `return string of saved date`() = runBlocking {
        val savedDate = "11-11-1111"

        Mockito
            .`when`(currenciesRepository.getSavedDate())
            .thenReturn(savedDate)

        val getDateUseCase = GetSavedDataUseCase(
            dispatcher = Dispatchers.IO,
            currenciesRepository = currenciesRepository
        )

        val actualDate = getDateUseCase.invoke()

        val expectedContainsTwoDash =
            actualDate[2].toString() == "-" && actualDate[5].toString() == "-"
        val expectedLength = "11-11-1111".length

        Assertions.assertEquals(true, expectedContainsTwoDash)
        Assertions.assertEquals(expectedLength, actualDate.length)
    }
}