package com.example.soliapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Receive the holiday data passed from the ListFragment using Safe Args
        // val holiday = DetailFragmentArgs.fromBundle(requireArguments()).holiday

        // Use holiday data to populate UI elements in the DetailFragment
        // binding.textViewHolidayName.text = holiday.name
        // binding.textViewHolidayDate.text = holiday.date
        // ...
    }
}
