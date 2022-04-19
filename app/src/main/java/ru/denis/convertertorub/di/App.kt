package ru.denis.convertertorub.di

import android.app.Application
import ru.denis.convertertorub.di.components.AppComponent
import ru.denis.convertertorub.di.components.DaggerAppComponent

open class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}