package ru.denis.convertertorub.di.modules

import dagger.Binds
import dagger.Module
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSource
import ru.denis.convertertorub.data.datasources.cbrfdatasource.CbRfDataSourceImpl

@Module
interface DataRepositoryModule {

    @Binds
    fun provideFocusStartApiSource(dataSource: CbRfDataSourceImpl): CbRfDataSource

}