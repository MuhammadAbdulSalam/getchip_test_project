package com.salam.getchip.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelper @Inject constructor(private val apiService: RetrofitBuilder) {
    suspend fun getDogsList() = apiService.getDogsList()
    suspend fun getDogImagesUrl(br: String) = apiService.getDogsUrl(br)
}