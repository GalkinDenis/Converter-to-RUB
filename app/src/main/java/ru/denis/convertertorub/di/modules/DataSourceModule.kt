package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSourceImpl
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSource
import ru.denis.convertertorub.data.datasources.localdatasource.LocalDataSourceImpl
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasource
import ru.denis.convertertorub.data.datasources.preferencedatasource.PreferenceDatasourceImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {

    @Binds
    @ViewModelScoped
    fun provideLocalDataSource(dataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    @ViewModelScoped
    fun provideCbRfDataSource(dataSource: CbRfDataSourceImpl): CbRfDataSource

    @Binds
    @ViewModelScoped
    fun providePreferencesDataSource(dataSource: PreferenceDatasourceImpl): PreferenceDatasource

}