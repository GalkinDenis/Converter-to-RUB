package ru.denis.convertertorub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import ru.denis.convertertorub.databinding.ActivityMainBinding
import android.view.MenuItem
import ru.denis.convertertorub.R
import ru.denis.convertertorub.ui.currenciesfragment.CurrenciesFragmentDirections

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.contacts -> {
                navController
                    .navigate(
                        CurrenciesFragmentDirections
                            .actionCurrenciesFragmentToConverterFragment()
                    )
                true
            }
            R.id.about -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}