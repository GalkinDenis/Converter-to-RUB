package ru.denis.convertertorub.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import ru.denis.convertertorub.di.qualifiers.IoDispatcher
import ru.denis.convertertorub.domain.usecases.baseusecases.OutSharedUseCase
import ru.denis.convertertorub.util.ResourcesStrings
import javax.inject.Inject

@ViewModelScoped
class GetResourcesStringsUseCase @Inject constructor(
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val resourcesStrings: ResourcesStrings
) : OutSharedUseCase<ResourcesStrings>(coroutineDispatcher) {

    override suspend fun execute() = resourcesStrings
}