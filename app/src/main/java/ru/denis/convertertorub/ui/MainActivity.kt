package ru.denis.convertertorub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.denis.convertertorub.R
import ru.denis.convertertorub.databinding.ActivityMainBinding
import ru.denis.convertertorub.ui.currenciesfragment.CurrenciesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, CurrenciesFragment())
                .addToBackStack("CurrenciesFragment")
                .commit()
        }
    }

}