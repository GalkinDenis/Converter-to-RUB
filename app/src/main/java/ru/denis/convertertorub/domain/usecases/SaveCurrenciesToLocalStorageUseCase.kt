package ru.denis.convertertorub.domain.usecases

import javax.inject.Inject

class SaveCurrenciesToLocalStorageUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke() {
        localRepository.saveCurrenciesToLocalStorage()
    }

}