package com.example.soliapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.soliapp.data.models.Holiday
import com.example.soliapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val holiday: Holiday = args.holiday

        // Use holiday data to populate UI elements in the DetailFragment
         binding.textViewCountryCodeValue.text = holiday.countryCode
         binding.textViewHolidayNameValue.text = holiday.name
         binding.textViewDateValue.text = holiday.date
         binding.textViewFavorite.text = holiday.isFavorite.toString()
        // ...
    }
}
