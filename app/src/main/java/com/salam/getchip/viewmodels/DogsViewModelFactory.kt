package com.salam.getchip.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salam.getchip.api.ApiHelper
import com.salam.getchip.data.DogsListRepository

class DogsViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogsListViewModel::class.java)) {
            return DogsListViewModel(DogsListRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}