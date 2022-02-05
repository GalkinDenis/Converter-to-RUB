package ru.denis.convertertorub.ui.converterfragment

import android.content.Context
import android.os.Bundle
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
import ru.denis.convertertorub.di.App
import ru.denis.convertertorub.presentation.converterfragmentviewmodel.ConverterFragmentViewModel
import javax.inject.Inject
import android.view.MenuItem
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.commit
import ru.denis.convertertorub.databinding.ConverterFragmentBinding
import ru.denis.convertertorub.ui.contactsfragment.ContactsFragment

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
        initObservers()
        initClickListeners()
    }

    private fun initObservers() {
        with(converterFragmentViewModel) {

            lifecycleScope.launchWhenStarted {
                aTypeCurrencyInFirstField().collect { result ->
                    binding.itemPriceLabel.hint = result
                }
            }

            lifecycleScope.launchWhenStarted {
                convertToOrFromState().collect { result ->
                    binding.targetValue.hint = result
                    binding.targetValue.refreshDrawableState()
                }
            }

            lifecycleScope.launchWhenStarted {
                suffixText().collect { result ->
                    binding.itemPriceLabel.suffixText = result
                }
            }

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
            with(converterFragmentViewModel) {

                fieldOfTargetValute.doAfterTextChanged {
                    getCodeAndValueCurrency(fieldOfTargetValute.text.toString())
                }

                topAppBar.setOnMenuItemClickListener { onMenuItemClickListener(it) }

                topAppBar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }

                changeConverterType.setOnClickListener {
                    changeTypeConverter(fieldOfTargetValute.text.toString())
                }

                convertButton.setOnClickListener {
                    val fieldOfRub = fieldOfRub.text.toString()
                    when {
                        (fieldOfRub.isBlank() || fieldOfTargetValute.text.toString().isBlank()) ->
                            showToast(getString(R.string.some_fields_is_empty))
                        else -> convert(fieldOfRub)
                    }
                }

            }
        }
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

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}