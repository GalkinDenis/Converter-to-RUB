package ru.denis.convertertorub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.denis.convertertorub.R
import ru.denis.convertertorub.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private val navigator = object : AppNavigator(this, R.id.main_container) {
        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment
        ) {
            fragmentTransaction.apply {
                setCustomAnimations(
                    R.anim.slide_in_from_bottom,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out_to_bottom
                )
            }
            super.setupFragmentTransaction(
                screen,
                fragmentTransaction,
                currentFragment,
                nextFragment
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }

    override fun onResume() {
        navigatorHolder.setNavigator(navigator)
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}