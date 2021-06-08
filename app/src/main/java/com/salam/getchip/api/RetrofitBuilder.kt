package com.salam.getchip.api

import com.salam.getchip.data.DogListResponse
import dagger.Subcomponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitBuilder {

    @GET("breeds/list/all")
    suspend fun getDogsList(): DogListResponse

    companion object{
        private const val BASE_URL = "https://dog.ceo/api/"
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        fun create(): RetrofitBuilder {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build() //Doesn't require the adapter
                .create(RetrofitBuilder::class.java)
        }
    }

}