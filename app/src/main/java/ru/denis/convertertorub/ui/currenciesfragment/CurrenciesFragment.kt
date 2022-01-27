package ru.denis.convertertorub.ui.currenciesfragment

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import ru.denis.convertertorub.R
import ru.denis.convertertorub.di.App
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.currenciesfragmentviewmodel.CurrenciesFragmentViewModel
import javax.inject.Inject
import android.view.*
import androidx.fragment.app.commit
import ru.denis.convertertorub.databinding.CurrenciesFragmentBinding
import ru.denis.convertertorub.ui.contactsfragment.ContactsFragment
import ru.denis.convertertorub.ui.converterfragment.ConverterFragment

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
        initClickListeners()
    }

    private fun onMenuItemClickListener(item: MenuItem) =
        when (item.itemId) {
            R.id.contacts -> {
                parentFragmentManager.commit {
                    replace(R.id.nav_host_fragment, ContactsFragment())
                    setReorderingAllowed(true)
                    addToBackStack("ContactsFragment")
                }
                true
            }
            else -> false
        }

    private fun initObservers() {
        with(currenciesFragmentViewModel) {

            lifecycleScope.launchWhenStarted {
                getListOfCurrencies().collect { listOfCurrencies ->
                    listOfCurrencies?.let { list ->
                        visibleRecyclerViewVisibility()
                        currenciesAdapter?.submitList(list)
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
            currenciesAdapter = CurrenciesAdapter()
            adapter = currenciesAdapter
        }
    }

    private fun initClickListeners() {
        binding.goToConverter.setOnClickListener { showConverterScreen() }
        binding.topAppBar.setOnMenuItemClickListener { onMenuItemClickListener(it) }
    }

    private fun showConverterScreen() {
            parentFragmentManager.commit {
                replace(R.id.nav_host_fragment, ConverterFragment())
                setReorderingAllowed(true)
                addToBackStack("ConverterFragment")
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

}