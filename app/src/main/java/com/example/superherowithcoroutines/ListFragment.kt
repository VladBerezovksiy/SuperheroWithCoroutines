package com.example.superherowithcoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView: RecyclerView = view.findViewById(R.id.recycleView)
        val viewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.getData()

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is MyViewModel.UiState.Empty -> Unit
                is MyViewModel.UiState.Processing -> "Processing..."
                is MyViewModel.UiState.Error -> "Error..."
                is MyViewModel.UiState.Result -> {
                    val myAdapter = MyRecycleViewAdapter(uiState.superheroList)
                    listView.adapter = myAdapter
                }
            }
        }

        listView.layoutManager = LinearLayoutManager(requireContext())
    }
}