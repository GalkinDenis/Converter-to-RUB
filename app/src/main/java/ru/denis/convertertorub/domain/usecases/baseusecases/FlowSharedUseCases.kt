package ru.denis.convertertorub.domain.usecases.baseusecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowSharedUseCases <out R>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke() = execute()
        .flowOn(coroutineDispatcher)

    protected abstract suspend fun execute(): Flow<List<R>>

}