package ru.denis.convertertorub.ui.currenciesfragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.databinding.ItemCurrenciesLayoutBinding

class CurrenciesViewHolder(
    private val binding: ItemCurrenciesLayoutBinding,
    private val onClick: (CurrencyEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemView: View, currencies: CurrencyEntity) {
        with(binding) {
            code.text = currencies.CharCode
            nominalValue.text = currencies.Nominal.toString()
            currencyName.text = currencies.Name
            course.text = currencies.Value.toString()
            changing.text = currencies.Difference.toString()
        }

        itemView.setOnClickListener {
            onClick(currencies)
        }
    }

}