package com.salam.getchip.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.salam.getchip.R
import com.salam.getchip.adapters.DogsGallaryRecyclerAdapter
import com.salam.getchip.adapters.DogsListRecyclerAdapter
import com.salam.getchip.api.ApiHelper
import com.salam.getchip.api.RetrofitBuilder
import com.salam.getchip.data.DogImagesUrl
import com.salam.getchip.data.DogNames
import com.salam.getchip.data.Status
import com.salam.getchip.databinding.FragmentDogDetailsBinding
import com.salam.getchip.databinding.FragmentDogListBinding
import com.salam.getchip.viewmodels.DogsListViewModel
import com.salam.getchip.viewmodels.DogsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogDetailsFragment : Fragment() {

    private lateinit var viewModel: DogsListViewModel
    private lateinit var binding: FragmentDogDetailsBinding
    private val adapter = DogsGallaryRecyclerAdapter()
    private val args: DogDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentDogDetailsBinding.inflate(inflater, container, false)

        binding.imagesList.adapter = adapter

        setupViewModel(args.dogName)
        setupObservers()

        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    private fun setupViewModel(breed: String) {
        viewModel = ViewModelProviders.of(
            this,
            DogsViewModelFactory(ApiHelper(RetrofitBuilder.create(), breedName = breed))
        ).get(DogsListViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getDogImageUrls().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        retrieveList(resource.data!!)
                        resource.data.forEach{ it ->
                            Log.d("----------", "-------------$it")
                        }
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun retrieveList(dogs: List<DogImagesUrl>) {
        adapter.submitList(dogs)
        adapter.notifyDataSetChanged()
    }



}