package com.salam.getchip.api

import com.salam.getchip.data.DogListResponse
import com.salam.getchip.data.DogsImageUrlsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton
/**
 * @author Muhammad Abdul Salam
 */

interface RetrofitBuilder {

    @GET("breeds/list/all")
    suspend fun getDogsList(): DogListResponse

    @GET("breed/{breed_name}/images")
    suspend fun getDogsUrl(@Path("breed_name") name: String): DogsImageUrlsResponse

    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"
        private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        private val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        fun create(): RetrofitBuilder {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitBuilder::class.java)
        }
    }
}