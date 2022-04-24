package ru.denis.convertertorub.ui.currenciesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import ru.denis.convertertorub.R
import ru.denis.convertertorub.presentation.ErrorType
import ru.denis.convertertorub.presentation.currenciesfragmentviewmodel.CurrenciesFragmentViewModel
import android.view.*
import androidx.fragment.app.commit
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.denis.convertertorub.databinding.CurrenciesFragmentBinding
import ru.denis.convertertorub.ui.contactsfragment.ContactsFragment
import ru.denis.convertertorub.ui.converterfragment.ConverterFragment

@AndroidEntryPoint
class CurrenciesFragment : Fragment() {

    private var currenciesAdapter: CurrenciesAdapter? = null

    private val currenciesFragmentViewModel by viewModels<CurrenciesFragmentViewModel>()

    private var _binding: CurrenciesFragmentBinding? = null
    private val binding get() = _binding!!

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

    private fun initViews() {
        binding.recyclerView.run {
            currenciesAdapter = CurrenciesAdapter()
            adapter = currenciesAdapter
        }
    }

    private fun initObservers() {
        with(currenciesFragmentViewModel) {

            lifecycleScope.launchWhenStarted {
                totalListOfCurrencies().collect { listOfCurrencies ->
                    listOfCurrencies?.let { list ->
                        currenciesAdapter?.submitList(list)
                    }
                    binding.swiperefresh.isRefreshing = false
                }
            }

            lifecycleScope.launchWhenStarted {
                showDate().collect { date ->
                    binding.lastDateUpdate.text = getString(R.string.last_date_update, date)
                }
            }

            showError().observe(viewLifecycleOwner) { errorType ->
                binding.swiperefresh.isRefreshing = false
                when (errorType) {
                    ErrorType.GET_ERROR -> showToast(getString(R.string.show_currencies_error))
                    ErrorType.LOAD_ERROR -> showToast(getString(R.string.load_currencies_error))
                    ErrorType.INSERT_ERROR -> showToast(getString(R.string.save_currencies_error))
                    else -> return@observe
                }
            }
        }
    }

    private fun initClickListeners() {
        with(binding) {
            swiperefresh.setOnRefreshListener {
                currenciesFragmentViewModel.getCurrencies()
                if(recyclerView.adapter?.itemCount != 0) {
                    swiperefresh.isRefreshing = false
                }
            }
            goToConverter.setOnClickListener { showConverterScreen() }
            topAppBar.setOnMenuItemClickListener { onMenuItemClickListener(it) }
            topAppBar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }
        }
    }

    private fun showConverterScreen() {
        parentFragmentManager.commit {
            replace(R.id.nav_host_fragment, ConverterFragment())
            setReorderingAllowed(true)
            addToBackStack("ConverterFragment")
        }
    }

    private fun showToast(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}