package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.denis.convertertorub.data.currenciesrepositoryimpl.CurrenciesRepositoryImpl
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSourceImpl
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSourceImpl
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasource
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasourceImpl
import ru.denis.convertertorub.domain.repository.CurrenciesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    fun provideLocalDataSource(dataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    fun provideCbRfDataSource(dataSource: CbRfDataSourceImpl): CbRfDataSource

    @Binds
    @Singleton
    fun providePreferencesDataSource(dataSource: PreferenceDatasourceImpl): PreferenceDatasource

}