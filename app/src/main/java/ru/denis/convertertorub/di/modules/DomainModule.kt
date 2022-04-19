package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.denis.convertertorub.data.currenciesrepositoryimpl.CurrenciesRepositoryImpl
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun provideCurrenciesRepository(dataSource: CurrenciesRepositoryImpl): CurrenciesRepository

}