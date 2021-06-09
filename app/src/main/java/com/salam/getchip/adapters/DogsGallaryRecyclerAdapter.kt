package com.salam.getchip.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.salam.getchip.data.DogImagesUrl
import com.salam.getchip.databinding.ListViewDogsImageBinding


class DogsGallaryRecyclerAdapter :
    ListAdapter<DogImagesUrl, RecyclerView.ViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DogGallaryViewHolder(
            ListViewDogsImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dog = getItem(position)
        (holder as DogGallaryViewHolder).bind(dog)
    }

    class DogGallaryViewHolder(
        private val binding: ListViewDogsImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.dogPhoto.let { dogName ->

                }
            }
        }

        fun bind(item: DogImagesUrl) {
            binding.apply {
                model = item
                executePendingBindings()
            }
        }
    }
}

private class ImageDiffCallback : DiffUtil.ItemCallback<DogImagesUrl>() {

    override fun areItemsTheSame(oldItem: DogImagesUrl, newItem: DogImagesUrl): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: DogImagesUrl, newItem: DogImagesUrl): Boolean {
        return oldItem == newItem
    }
}