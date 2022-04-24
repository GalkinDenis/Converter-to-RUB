package ru.denis.convertertorub.domain.usecases.baseusecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class InOutSharedUseCase<in T, out R>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(parameter: T) =
        withContext(coroutineDispatcher) {
            execute(parameter)
        }

    protected abstract suspend fun execute(parameter: T): R

}