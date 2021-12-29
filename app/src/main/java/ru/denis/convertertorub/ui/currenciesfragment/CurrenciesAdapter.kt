package ru.denis.convertertorub.ui.currenciesfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.databinding.ItemCurrenciesLayoutBinding
import javax.inject.Inject

class CurrenciesAdapter @Inject constructor(
    private val onClick: (CurrencyEntity) -> Unit
) :
    RecyclerView.Adapter<CurrenciesViewHolder>() {

    var listOfCurrencies = emptyList<CurrencyEntity>()
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
            ),
            onClick
        )

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(holder.itemView, listOfCurrencies[position])
    }
}