package com.example.soliapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soliapp.common.ResponseState
import com.example.soliapp.data.models.Holiday
import com.example.soliapp.databinding.FragmentListBinding
import com.example.soliapp.ui.HolidayViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment(), MainListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentListBinding
    private val viewModel: HolidayViewModel by activityViewModels()
    private lateinit var mainListAdapter: MainListAdapter

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
        //RECYCLER
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            DividerItemDecoration.VERTICAL
        )

        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        mainListAdapter = MainListAdapter(this)
        binding.recyclerView.adapter = mainListAdapter

        // Observe LiveData from ViewModel and update the RecyclerView when data changes
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    // State is SUCCESS, display the response list
                    val holidays = state.holidayList
                    mainListAdapter.submitList(holidays)
                    // Hide any failure-related UI elements if previously displayed
                    binding.failureTextView.visibility = View.GONE
                }
                is ResponseState.Error -> {
                    // State is ERROR, display "FAILURE" text
                    mainListAdapter.submitList(emptyList()) // Clear the list
                    binding.failureTextView.visibility = View.VISIBLE
                    binding.failureTextView.text = "FAILURE: ${state.errorMessage}"
                }
                is ResponseState.Loading -> {
                    // State is LOADING, you can show a loading indicator here if desired
                    binding.failureTextView.visibility = View.GONE
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fetchData()
        }

    }

    override fun onItemTextClick(item: Holiday) {
// Use Safe Args to navigate and pass the Holiday object
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
        findNavController().navigate(action)
    }

    override fun onToggleFavoriteClick(item: Holiday, isChecked: Boolean) {
//        Toast.makeText(requireContext(), "${item.name} , isChecked: $isChecked", Toast.LENGTH_LONG).show()
    }
}
