package ru.denis.convertertorub.ui.currenciesfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.denis.convertertorub.databinding.CurrenciesItemViewHolderBinding

class CurrenciesAdapter() :
    RecyclerView.Adapter<CurrenciesViewHolder>() {

    private var listOfCurrencies = emptyList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int =
        listOfCurrencies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder =
        CurrenciesViewHolder(
            CurrenciesItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(holder.itemView, listOfCurrencies[position])
    }
}