package ru.denis.convertertorub.ui.currenciesfragment

import androidx.recyclerview.widget.DiffUtil
import ru.denis.convertertorub.domain.entities.ReadyCurrencies

object CurrenciesDiffUtils: DiffUtil.ItemCallback<ReadyCurrencies>() {
    override fun areItemsTheSame(oldItem: ReadyCurrencies, newItem: ReadyCurrencies): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ReadyCurrencies, newItem: ReadyCurrencies): Boolean {
        return oldItem._ID == newItem._ID
                && oldItem.numCode == newItem.numCode
                && oldItem.charCode == newItem.charCode
                && oldItem.name == newItem.name
                && oldItem.value == newItem.value
                && oldItem.difference == newItem.difference

    }
}