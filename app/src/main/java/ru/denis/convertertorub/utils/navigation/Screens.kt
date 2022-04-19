package ru.denis.convertertorub.utils.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.denis.convertertorub.ui.contactsfragment.ContactsFragment
import ru.denis.convertertorub.ui.converterfragment.ConverterFragment

@Suppress("FunctionName")
object Screens {

    fun ConverterScreen() = FragmentScreen {
        ConverterFragment()
    }

    fun ContactsScreen() = FragmentScreen {
        ContactsFragment()
    }

}