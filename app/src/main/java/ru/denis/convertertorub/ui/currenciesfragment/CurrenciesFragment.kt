package ru.denis.convertertorub.ui.currenciesfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import ru.denis.convertertorub.R
import ru.denis.convertertorub.databinding.CurrenciesFragmentBinding
import ru.denis.convertertorub.di.App
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.currenciesfragmentviewmodel.CurrenciesFragmentViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrenciesFragment : Fragment() {

    private var currenciesAdapter: CurrenciesAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val currenciesFragmentViewModel by viewModels<CurrenciesFragmentViewModel> { viewModelFactory }

    private var _binding: CurrenciesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App)
            .appComponent.injectCurrenciesFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrenciesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        with(currenciesFragmentViewModel) {

            lifecycleScope.launchWhenStarted {
                getListOfCurrencies().collect { listOfCurrencies ->
                    listOfCurrencies?.let { list ->
                        visibleRecyclerViewVisibility()
                        currenciesAdapter?.listOfCurrencies = list
                    }
                    goneProgressbarVisibility()
                }
            }

            lifecycleScope.launchWhenStarted {
            showDate().collect { date ->
                    binding.lastDateUpdate.text =
                        getString(R.string.last_date_update, date)
                }
            }



            showError().observe(viewLifecycleOwner) { errorType ->
                when (errorType) {
                    ErrorType.GET_ERROR -> showToast(getString(R.string.show_currencies_error))
                    ErrorType.LOAD_ERROR -> showToast(getString(R.string.load_currencies_error))
                    ErrorType.INSERT_ERROR -> showToast(getString(R.string.save_currencies_error))
                    else -> return@observe
                }
                goneProgressbarVisibility()
            }

        }
    }

    private fun initViews() {
        binding.recyclerView.apply {
            currenciesAdapter = CurrenciesAdapter(
                onClick = { currencies ->
                    println("111111111111111111111")
                }
            )
            adapter = currenciesAdapter
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun goneProgressbarVisibility() {
        binding.progressBar.visibility = View.GONE
    }

    private fun visibleRecyclerViewVisibility() {
        binding.recyclerView.visibility = View.VISIBLE
    }

/*


        val adapter = ValuteListAdapter()
        binding.recyclerView.adapter = adapter

        val progressbar = binding.progressBar

        //Обновление списка валют из базы данных.
        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            when {

                //Заполнение списка данными из базы данных, если она не пустая.
                items.isNotEmpty() -> {
                    binding.note.visibility = View.INVISIBLE
                    ItemRoomDatabase.tableIsDefined = true
                }

                //Загрузка списка валют из сети, если база данных пустая и не очищена вручную.
                (items.isEmpty() && !viewModel.clearState) -> {
                    viewModel.fillingOfList()
                }

                //Отображение сообщения о возможности загрузить список валют.
                viewModel.clearState -> {
                    binding.note.visibility = View.VISIBLE
                    ItemRoomDatabase.tableIsDefined = false
                }
            }

            //Обновление списка.
            items.let {
                adapter.submitList(it)
            }
        }

        //Демонстрация прогресса загрузки, предоставляемая ProgressBar.
        viewModel.getPercentOfLoad().observe(this.viewLifecycleOwner) {
            progressbar.visibility = View.VISIBLE
            progressbar.progress = (((it.toDouble()) / 34.0) * 100.0).toInt()
            if (progressbar.progress == 100) progressbar.visibility = View.GONE
        }


        //Вывод всплывающего сообщения в случае неудачи сетевого запроса.
        viewModel.networkResponse.observe(this.viewLifecycleOwner) { networkResponse ->
            when (networkResponse) {
                ValutesApiStatus.ERROR -> {
                    binding.note.visibility = View.VISIBLE
                    ItemRoomDatabase.tableIsDefined = false

                    Toast.makeText(
                        context,
                        getString(R.string.network_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> return@observe
            }
        }

        binding.reloadButton.setOnClickListener {
            //Функция заполнение списка валют.
            if (!ItemRoomDatabase.tableIsDefined) {
                //SQL -> insert.
                viewModel.fillingOfList()
            } else {
                //SQL -> update.
                viewModel.updatingOfList()

            }
        }

        binding.clearButton.setOnClickListener {
            //Функция очистки списка валют.
            viewModel.clearTable()
            binding.note.visibility = View.VISIBLE
            ItemRoomDatabase.tableIsDefined = false
        }

 */


}