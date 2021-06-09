package com.salam.getchip.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.salam.getchip.adapters.DogsGallaryRecyclerAdapter
import com.salam.getchip.data.DogImagesUrl
import com.salam.getchip.data.Status
import com.salam.getchip.databinding.FragmentDogDetailsBinding
import com.salam.getchip.viewmodels.DogsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogDetailsFragment : Fragment() {

    private val viewModel: DogsListViewModel by viewModels()
    private val adapter = DogsGallaryRecyclerAdapter()
    private val args: DogDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val binding = FragmentDogDetailsBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.imagesList.adapter = adapter
        setupObservers()
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        return binding.root
    }

    private fun setupObservers() {
        viewModel.getDogImageUrls(args.dogName).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        retrieveList(resource.data!!)
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