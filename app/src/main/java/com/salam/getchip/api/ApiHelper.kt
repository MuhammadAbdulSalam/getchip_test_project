package com.salam.getchip.api

import javax.inject.Inject

class ApiHelper(private val apiService: RetrofitBuilder, private val breedName: String) {
    suspend fun getDogsList() = apiService.getDogsList()
    suspend fun getDogImagesUrl() = apiService.getDogsUrl(breedName)
}