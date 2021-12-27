package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSourceImpl
import ru.denis.convertertorub.data.datasources.dbdatasource.DbDataSource
import ru.denis.convertertorub.data.datasources.dbdatasource.DbDataSourceImpl

@Module
interface DataRepositoryModule {

    @Binds
    fun provideDbDataSource(dataSource: DbDataSourceImpl): DbDataSource

    @Binds
    fun provideFocusStartApiSource(dataSource: CbRfDataSourceImpl): CbRfDataSource

}