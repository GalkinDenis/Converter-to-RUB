package ru.denis.convertertorub.domain.usecases.baseusecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class InSharedUseCase<R>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(inParameter: R) =
        withContext(coroutineDispatcher) {
            execute(inParameter)
        }

    protected abstract suspend fun execute(inParameter: R)

}