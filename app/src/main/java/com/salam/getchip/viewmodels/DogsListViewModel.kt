package com.salam.getchip.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.salam.getchip.data.DogsListRepository
import com.salam.getchip.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * @author Muhammad Abdul Salam
 * We do not need seperate models since its a very small and basic app
 * One View Models will do the work just fine
 */

@HiltViewModel
class DogsListViewModel @Inject constructor(
    private val mainRepository: DogsListRepository
) : ViewModel() {

    fun getDogsList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getDogsStringList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getDogImageUrls(breedName: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getDogImagesUrl(breedName)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
