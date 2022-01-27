package ru.denis.convertertorub.ui.contactsfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.denis.convertertorub.R
import ru.denis.convertertorub.databinding.ContactsFragmentBinding
import ru.denis.convertertorub.databinding.ConverterFragmentBinding

class ContactsFragment : Fragment() {

    private var _binding: ContactsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ContactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            topAppBar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }
            moveToSource.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.reference_on_my_github))
                )
                startActivity(browserIntent)
            }
        }
    }

}