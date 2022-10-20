package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.denis.convertertorub.data.currenciesrepositoryimpl.CurrenciesRepositoryImpl
import ru.denis.convertertorub.domain.repository.CurrenciesRepository

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    @ViewModelScoped
    fun provideCurrenciesRepository(dataSource: CurrenciesRepositoryImpl): CurrenciesRepository
}