package ru.denis.convertertorub.ui.currenciesfragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.StateFlow
import ru.denis.convertertorub.data.datasources.database.CurrencyEntityTable
import ru.denis.convertertorub.databinding.ItemCurrenciesLayoutBinding

class CurrenciesViewHolder(
    private val binding: ItemCurrenciesLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currencies: CurrencyEntityTable) {
        with(binding) {
            code.text = currencies.charCode
            currencyName.text = currencies.name
            course.text = currencies.value
            if (currencies.difference.startsWith("-")) changing.setTextColor(Color.RED) else changing.setTextColor(Color.GREEN)
            changing.text = currencies.difference
        }
    }

}