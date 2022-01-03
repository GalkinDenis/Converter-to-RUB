package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSourceImpl
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSourceImpl
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasource
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasourceImpl

@Module
interface DataRepositoryModule {

    @Binds
    fun provideLocalDataSource(dataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    fun provideCbRfDataSource(dataSource: CbRfDataSourceImpl): CbRfDataSource

    @Binds
    fun providePreferencesDataSource(dataSource: PreferenceDatasourceImpl): PreferenceDatasource

}