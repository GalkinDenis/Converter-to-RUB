package ru.denis.convertertorub.ui.contactsfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import ru.denis.convertertorub.R
import ru.denis.convertertorub.databinding.ContactsFragmentBinding
import javax.inject.Inject

@AndroidEntryPoint
class ContactsFragment : Fragment() {

    @Inject lateinit var router: Router

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
            topAppBar.setNavigationOnClickListener { router.exit() }
            moveToSource.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.reference_on_my_github))
                )
                startActivity(browserIntent)
            }

            art.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.reference_on_irinabush_instagram))
                )
                startActivity(browserIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}