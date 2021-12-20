package ru.denis.convertertorub.ui.currenciesfragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.denis.convertertorub.databinding.CurrenciesItemViewHolderBinding

class CurrenciesViewHolder(
    private val binding: CurrenciesItemViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemView: View, currencies: Loans) {
        with(binding) {
            id.text = loans.id.toString()
            firstName.text = loans.firstName
            lastName.text = loans.lastName
        }

    }
}