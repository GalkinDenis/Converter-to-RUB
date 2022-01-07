package ru.denis.convertertorub.ui.converterfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.denis.convertertorub.databinding.ConverterFragmentBinding
import ru.denis.convertertorub.di.App
import ru.denis.convertertorub.presentation.converterfragmentviewmodel.ConverterFragmentViewModel
import javax.inject.Inject

class ConverterFragment : Fragment() {
//custom_shadow
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<ConverterFragmentViewModel> { viewModelFactory }

    private var _binding: ConverterFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var targetValuteName: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.injectConverterFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ConverterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /* binding.fieldOfRub.setText(viewModel.inputField)
         binding.resultValueShow.text = viewModel.resultOfDevide ?: "-"

         //Адаптер для выпадающего списка имени стран.
         val adapter = activity?.let {
             ArrayAdapter.createFromResource(
                 it.applicationContext,
                 R.array.currencies_name_list,
                 android.R.layout.simple_spinner_dropdown_item
             )
         }
         binding.fieldOfTargetValute.setAdapter(adapter)


         //Блок отвечающий за конвертацию валют и обработку состояния полей ввода.
         /////////////////////////////////////////////////////////////////////////
         binding.convert.setOnClickListener {
             targetValuteName = binding.fieldOfTargetValute.text.toString()
             val fieldOfRubIsEmpty = binding.fieldOfRub.text.toString()

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
         }
         /////////////////////////////////////////////////////////////////////////

          */

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}