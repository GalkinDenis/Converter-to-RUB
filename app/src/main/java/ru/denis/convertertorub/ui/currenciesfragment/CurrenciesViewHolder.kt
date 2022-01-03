package ru.denis.convertertorub.ui.currenciesfragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.StateFlow
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
            course.text = currencies.Value
            if (currencies.Difference.startsWith("-")) changing.setTextColor(Color.RED) else changing.setTextColor(Color.GREEN)
            changing.text = currencies.Difference
        }

        itemView.setOnClickListener {
            onClick(currencies)
        }
    }

}