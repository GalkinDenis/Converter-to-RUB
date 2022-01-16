package ru.denis.convertertorub.ui.contactsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.denis.convertertorub.databinding.ContactsFragmentBinding

class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ContactsFragmentBinding.inflate(inflater, container, false).root
    }

}