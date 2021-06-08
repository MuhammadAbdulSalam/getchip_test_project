package com.salam.getchip.api

import javax.inject.Inject

class ApiHelper(private val apiService: RetrofitBuilder) {
    suspend fun getDogsList() = apiService.getDogsList()
}