package ru.denis.convertertorub.ui.currenciesfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable
import ru.denis.convertertorub.databinding.ItemCurrenciesLayoutBinding
import javax.inject.Inject

class CurrenciesAdapter @Inject constructor() :
    RecyclerView.Adapter<CurrenciesViewHolder>() {

    var listOfCurrencies = emptyList<CurrencyEntityTable>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = listOfCurrencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder =
        CurrenciesViewHolder(
            ItemCurrenciesLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(listOfCurrencies[position])
    }
}