package com.example.soliapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.soliapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: HolidayViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the RecyclerView and adapter using binding.recyclerView

        // Observe LiveData from ViewModel and update the RecyclerView when data changes
        viewModel.state.observe(viewLifecycleOwner) { holidays ->
            // Update RecyclerView adapter with the list of holidays
            Log.d("zzzzz", holidays.toString())
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fetchData()
        }

        // Set item click listener to navigate to the DetailFragment
        // For example:
        // recyclerView.adapter.setOnItemClickListener { holiday ->
        //     val action = ListFragmentDirections.actionListFragmentToDetailFragment(holiday)
        //     findNavController().navigate(action)
        // }
    }
}
