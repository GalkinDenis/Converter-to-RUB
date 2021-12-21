package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import ru.denis.convertertorub.data.remoterepositoryimpl.RemoteRepositoryImpl
import ru.denis.convertertorub.domain.repository.RemoteRepository
import javax.inject.Singleton

@Module
interface DomainModule {
    @Singleton
    @Binds
    fun provideRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository
}