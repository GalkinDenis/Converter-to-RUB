package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import ru.denis.convertertorub.data.remoterepositoryimpl.CurrenciesRepositoryImpl
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Singleton

@Module
interface DomainModule {
    @Singleton
    @Binds
    fun provideRemoteRepository(repository: CurrenciesRepositoryImpl): CurrenciesRepository
}