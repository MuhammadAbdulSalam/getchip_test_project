package com.salam.getchip.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salam.getchip.data.DogNames
import com.salam.getchip.databinding.ListItemDogsListNameBinding
import com.salam.getchip.fragments.DogViewPagerFragmentDirections

class DogsListRecyclerAdapter: ListAdapter<DogNames, RecyclerView.ViewHolder>(DogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DogNameViewHolder(
            ListItemDogsListNameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dog = getItem(position)
        (holder as DogNameViewHolder).bind(dog)
    }

    class DogNameViewHolder(
        private val binding: ListItemDogsListNameBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.dog?.let { dogName ->
                    navigateToDetails(dogName, it)
                }
            }
        }

        private fun navigateToDetails(
            dogName: DogNames,
            view: View
        ) {
            val direction =
                DogViewPagerFragmentDirections.actionDogViewPagerToDogDetailFragment(
                    dogName.name
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: DogNames) {
            binding.apply {
                dog = item
                executePendingBindings()
            }
        }
    }
}

private class DogDiffCallback : DiffUtil.ItemCallback<DogNames>() {

    override fun areItemsTheSame(oldItem: DogNames, newItem: DogNames): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DogNames, newItem: DogNames): Boolean {
        return oldItem == newItem
    }
}