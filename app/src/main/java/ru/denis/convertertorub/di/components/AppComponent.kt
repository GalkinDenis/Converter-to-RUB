package ru.denis.convertertorub.di.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.denis.convertertorub.di.modules.*
import ru.denis.convertertorub.ui.currenciesfragment.CurrenciesFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DataRepositoryModule::class,
        DomainModule::class,
        PresentationModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    @Singleton
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun injectCurrenciesFragment(fragment: CurrenciesFragment)
    //fun injectLoginRegistrationFragment(fragment: LoginRegistrationFragment)

}

