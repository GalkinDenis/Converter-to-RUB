package ru.denis.convertertorub.ui.converterfragment

import android.content.Context
import android.content.Intent
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
import android.net.Uri
import android.view.MenuItem
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
            binding.topAppBar.setOnMenuItemClickListener { onMenuItemClickListener(it) }
            topAppBar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }

            convertButton.setOnClickListener {
                val targetCurrencyName = fieldOfTargetValute.text.toString()
                val fieldOfRub = fieldOfRub.text.toString()
                when {
                    (fieldOfRub.isBlank() || targetCurrencyName.isBlank()) ->
                        showToast(getString(R.string.some_fields_is_empty))
                    else -> converterFragmentViewModel.convert(fieldOfRub, targetCurrencyName)
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

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}