package ru.denis.convertertorub.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.denis.convertertorub.data.datasources.database.CurrencyDataBase
import ru.denis.convertertorub.data.datasources.database.ItemDao
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideDataStore(context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("app_prefs")
        }

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

