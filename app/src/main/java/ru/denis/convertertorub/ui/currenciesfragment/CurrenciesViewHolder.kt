package ru.denis.convertertorub.ui.currenciesfragment

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.denis.convertertorub.R
import ru.denis.convertertorub.data.datasources.database.CurrencyEntity
import ru.denis.convertertorub.databinding.ItemCurrenciesLayoutBinding

class CurrenciesViewHolder(
    private val binding: ItemCurrenciesLayoutBinding,
    private val onClick: (CurrencyEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemView: View, currencies: CurrencyEntity) {
        with(binding) {
            code.text = currencies.CharCode
            currencyName.text = currencies.Name
            course.text = currencies.Value.toString()
            val difference = currencies.Difference
            if (difference >= 0) changing.setTextColor(Color.GREEN) else changing.setTextColor(Color.RED)
            changing.text = difference.toString()
        }

        itemView.setOnClickListener {
            onClick(currencies)
        }
    }

}