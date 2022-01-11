package ru.denis.convertertorub.ui.converterfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import ru.denis.convertertorub.R
import ru.denis.convertertorub.databinding.ConverterFragmentBinding
import ru.denis.convertertorub.di.App
import ru.denis.convertertorub.presentation.converterfragmentviewmodel.ConverterFragmentViewModel
import ru.denis.convertertorub.ui.currenciesfragment.CurrenciesAdapter
import javax.inject.Inject

class ConverterFragment : Fragment() {

    private var adapter: ArrayAdapter<CharSequence>? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val converterFragmentViewModel by viewModels<ConverterFragmentViewModel> { viewModelFactory }

    private var _binding: ConverterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.injectConverterFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ConverterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //createTargetCurrencyAdapter()
        initObservers()
        initClickListeners()
    }

    /*
when {
    (ItemRoomDatabase.tableIsDefined && fieldOfRubIsEmpty.isNotBlank() && targetValuteName.isNotBlank()) ->
        //Слушатель получения элемента базы данных.
        viewModel.getItemValue(targetValuteName).observe(this.viewLifecycleOwner) {
                itemTableValue ->
            //Функция конвертации.
            viewModel.toDevide(fieldOfRubIsEmpty, itemTableValue)
            //Вывод результат конвертации.
            binding.resultValueShow.text = viewModel.resultOfDevide
        }

    //Если оба поля пустые.
    (fieldOfRubIsEmpty.isBlank() && targetValuteName.isBlank()) -> Toast.makeText(
        context,
        getString(R.string.rub_and_valute_empty),
        Toast.LENGTH_LONG
    ).show()

    //Если данные не были загружены.
    !ItemRoomDatabase.tableIsDefined -> Toast.makeText(
        context,
        getString(R.string.data_was_do_not_loaded),
        Toast.LENGTH_LONG
    ).show()

    //Если поле - "ЦЕЛЕВАЯ ВАЛЮТА ДЛЯ КОНВЕРТАЦИИ" пустое.
    targetValuteName.isBlank() -> Toast.makeText(
        context,
        getString(R.string.target_convert_valute_empty),
        Toast.LENGTH_LONG
    ).show()

    //Если поле - ЗНАЧЕНИЕ В РУБЛЯХ пустое
    fieldOfRubIsEmpty.isBlank() -> Toast.makeText(
        context,
        getString(R.string.rub_field_empty),
        Toast.LENGTH_LONG
    ).show()
}

     */

    private fun createTargetCurrencyAdapter() {
        adapter = activity?.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.currencies_name_list,
                android.R.layout.simple_spinner_dropdown_item
            )
        }
        binding.fieldOfTargetValute.setAdapter(adapter)
    }

    private fun initObservers() {
        with(converterFragmentViewModel) {

            lifecycleScope.launchWhenStarted {
                divisionResult().collect { result ->
                    binding.resultValueShow.text = result
                    createTargetCurrencyAdapter()
                }
            }

            showError().observe(viewLifecycleOwner) {
                showToast(getString(R.string.show_currencies_error))
            }

        }
    }


    private fun initClickListeners() {
        with(binding) {
            convertButton.setOnClickListener {
                val targetCurrencyName = fieldOfTargetValute.text.toString()
                val fieldOfRub = fieldOfRub.text.toString()
                when {
                    (fieldOfRub.isBlank() || targetCurrencyName.isBlank()) ->
                        Toast.makeText(
                            context,
                            getString(R.string.some_fields_is_empty),
                            Toast.LENGTH_LONG
                        ).show()

                    else -> converterFragmentViewModel.convert(fieldOfRub, targetCurrencyName)
                }
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}