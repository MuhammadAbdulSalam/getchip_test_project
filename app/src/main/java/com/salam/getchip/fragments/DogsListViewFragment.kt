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
import com.salam.getchip.databinding.FragmentDogListViewBinding
import com.salam.getchip.viewmodels.DogsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogsListViewFragment : Fragment() {
    private val viewModel: DogsListViewModel by viewModels()
    private val adapter = DogsListRecyclerAdapter()
    private var currentCount = 0
    private lateinit var binding: FragmentDogListViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDogListViewBinding.inflate(inflater, container, false)

        context ?: return binding.root
        binding.dogsList.adapter = adapter
        binding.moveToTop.setOnClickListener {
            if (currentCount > 0) binding.dogsList.smoothScrollToPosition(0)
        }
        binding.moveToEnd.setOnClickListener {
            if (currentCount > 0) binding.dogsList.smoothScrollToPosition(currentCount)
        }
        binding.refreshPage.setOnClickListener {
            setupObservers()
            if (currentCount > 0) binding.dogsList.smoothScrollToPosition(0)
        }
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.getDogsList().observe(viewLifecycleOwner, {
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

    private fun retrieveList(dogs: List<DogNames>) {
        currentCount = dogs.size
        adapter.submitList(dogs)
        adapter.notifyDataSetChanged()
    }

    fun updateUI(recycler: Int, progressbar: Int, errorMessage: Int) {
        binding.dogsList.visibility = recycler
        binding.listViewProgress.visibility = progressbar
        binding.tvErrorMessage.visibility = errorMessage
    }

}