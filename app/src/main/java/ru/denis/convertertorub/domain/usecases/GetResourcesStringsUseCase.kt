package ru.denis.convertertorub.domain.usecases

import ru.denis.convertertorub.presentation.ResourcesStrings
import javax.inject.Inject

class GetResourcesStringsUseCase @Inject constructor(
    private val resourcesStrings: ResourcesStrings
) {

     operator fun invoke() = resourcesStrings

}