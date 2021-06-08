package com.salam.getchip.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.salam.getchip.adapters.DogsListRecyclerAdapter
import com.salam.getchip.api.ApiHelper
import com.salam.getchip.api.RetrofitBuilder
import com.salam.getchip.data.DogNames
import com.salam.getchip.data.Status
import com.salam.getchip.databinding.FragmentDogListBinding
import com.salam.getchip.viewmodels.DogsListViewModel
import com.salam.getchip.viewmodels.DogsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject


@AndroidEntryPoint
class DogListFragment : Fragment() {
    private lateinit var viewModel: DogsListViewModel
    private lateinit var binding: FragmentDogListBinding
    private val adapter = DogsListRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDogListBinding.inflate(inflater, container, false)

        binding.dogsList.adapter = adapter

        setupViewModel()
        setupObservers()

        return binding.root
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            DogsViewModelFactory(ApiHelper(RetrofitBuilder.create()))
        ).get(DogsListViewModel::class.java)
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