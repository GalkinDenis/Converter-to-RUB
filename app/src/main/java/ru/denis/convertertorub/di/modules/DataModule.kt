package ru.denis.convertertorub.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.denis.convertertorub.data.datasources.database.CurrencyDataBase
import ru.denis.convertertorub.data.datasources.database.ItemDao
import ru.denis.convertertorub.data.datasources.dbdatasource.DbDataSource
import ru.denis.convertertorub.data.datasources.dbdatasource.DbDataSourceImpl
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideDao(dataBase: CurrencyDataBase): ItemDao = dataBase.itemDao()


    @Singleton
    @Provides
    fun provideDataBase(context: Context): CurrencyDataBase =
        Room.databaseBuilder(
            context.applicationContext,
            CurrencyDataBase::class.java,
            "CbRfApiDataBase.db"
        ).build()

}

