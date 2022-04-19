package ru.denis.convertertorub.ui.currenciesfragment

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import ru.denis.convertertorub.databinding.ItemCurrenciesLayoutBinding
import ru.denis.convertertorub.domain.entities.ReadyCurrencies

class CurrenciesViewHolder(
    private val binding: ItemCurrenciesLayoutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currencies: ReadyCurrencies) {
        with(binding) {
            code.text = currencies.charCode
            currencyName.text = currencies.name
            course.text = currencies.value
            if (currencies.difference.startsWith("-")) changing.setTextColor(Color.rgb(209, 111,  111))
            else changing.setTextColor(Color.rgb(142, 222,  133))
            changing.text = currencies.difference
        }
    }

}