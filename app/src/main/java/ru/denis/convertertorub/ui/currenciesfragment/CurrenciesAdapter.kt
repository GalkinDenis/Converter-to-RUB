package ru.denis.convertertorub.ui.currenciesfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.denis.convertertorub.databinding.ItemCurrenciesLayoutBinding
import ru.denis.convertertorub.domain.entities.ReadyCurrencies

class CurrenciesAdapter
    : ListAdapter<ReadyCurrencies, CurrenciesViewHolder>(CurrenciesDiffUtils) {

    /*
    var listOfCurrencies = emptyList<ReadyCurrencies>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

     */

    //override fun getItemCount() = listOfCurrencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder =
        CurrenciesViewHolder(
            ItemCurrenciesLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}