package ru.denis.convertertorub.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.denis.convertertorub.di.key.ViewModelKey
import ru.denis.convertertorub.presentation.ViewModelFactory
import ru.denis.convertertorub.presentation.converterfragmentviewmodel.ConverterFragmentViewModel
import ru.denis.convertertorub.presentation.currenciesfragmentviewmodel.CurrenciesFragmentViewModel
import javax.inject.Singleton

@Module
interface PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrenciesFragmentViewModel::class)
    fun provideCurrenciesFragmentViewModel(viewModel: CurrenciesFragmentViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ConverterFragmentViewModel::class)
    fun provideConverterFragmentViewModel(viewModel: ConverterFragmentViewModel): ViewModel


    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}