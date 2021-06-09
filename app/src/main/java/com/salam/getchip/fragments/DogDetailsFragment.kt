package com.salam.getchip.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.salam.getchip.adapters.DogsGalleryRecyclerAdapter
import com.salam.getchip.data.DogImagesUrl
import com.salam.getchip.data.Status
import com.salam.getchip.databinding.FragmentDogDetailsBinding
import com.salam.getchip.viewmodels.DogsListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Muhammad Abdul Salam
 * Details Fragment for Dog Images, Can include other details if needed
 */


@AndroidEntryPoint
class DogDetailsFragment : Fragment() {

    private val viewModel: DogsListViewModel by viewModels()
    private val adapter = DogsGalleryRecyclerAdapter()
    private val args: DogDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDogDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentDogDetailsBinding.inflate(inflater, container, false)
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
                        updateUI(
                            recycler = View.VISIBLE,
                            progressbar = View.GONE,
                            errorMessage = View.GONE
                        )
                    }
                    Status.ERROR -> {
                        updateUI(
                            recycler = View.INVISIBLE,
                            progressbar = View.GONE,
                            errorMessage = View.VISIBLE
                        )
                    }
                    Status.LOADING -> {
                        updateUI(
                            recycler = View.INVISIBLE,
                            progressbar = View.VISIBLE,
                            errorMessage = View.GONE
                        )
                    }
                }
            }
        })
    }

    private fun retrieveList(dogs: List<DogImagesUrl>) {
        adapter.submitList(dogs)
        adapter.notifyDataSetChanged()
    }

    private fun updateUI(recycler: Int, progressbar: Int, errorMessage: Int) {
        binding.imagesList.visibility = recycler
        binding.listViewProgress.visibility = progressbar
        binding.tvErrorMessage.visibility = errorMessage
    }


}