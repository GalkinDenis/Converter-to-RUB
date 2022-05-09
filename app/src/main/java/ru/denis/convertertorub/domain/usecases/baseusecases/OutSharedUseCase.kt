package ru.denis.convertertorub.domain.usecases.baseusecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class OutSharedUseCase<out R>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke() =
        withContext(coroutineDispatcher) {
            execute()
        }

    protected abstract suspend fun execute(): R
}