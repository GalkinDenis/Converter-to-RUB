package ru.denis.convertertorub.data.remoterepositoryimpl

import ru.denis.convertertorub.data.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val cbRfDataSource: CbRfDataSource
) : RemoteRepository {

    override suspend fun getCurrenciesFromRemote() =
        cbRfDataSource.getCurrenciesFromRemote()

}