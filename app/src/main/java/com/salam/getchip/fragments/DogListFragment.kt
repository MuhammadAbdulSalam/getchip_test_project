package com.salam.getchip.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.salam.getchip.adapters.DogsListRecyclerAdapter
import com.salam.getchip.data.DogNames
import com.salam.getchip.data.Status
import com.salam.getchip.databinding.FragmentDogListBinding
import com.salam.getchip.viewmodels.DogsListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DogListFragment : Fragment() {
    private val viewModel: DogsListViewModel by viewModels()
    private val adapter = DogsListRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        val binding = FragmentDogListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.dogsList.adapter = adapter
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.getDogsList().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        retrieveList(resource.data!!)
                    }
                    Status.ERROR -> { }
                    Status.LOADING -> { }
                }
            }
        })
    }

    private fun retrieveList(dogs: List<DogNames>) {
        adapter.submitList(dogs)
        adapter.notifyDataSetChanged()
    }



}