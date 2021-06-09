package com.salam.getchip.data

import com.salam.getchip.api.ApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsListRepository @Inject constructor(private val apiHelper: ApiHelper)  {

    suspend fun getDogsStringList(): List<DogNames>{
        val list = arrayListOf<DogNames>()
        apiHelper.getDogsList().message.keySet().iterator().forEach {
            val dogName = DogNames(it)
            list.add(dogName)
        }
        return list
    }

    suspend fun getDogImagesUrl(breedName: String): List<DogImagesUrl>{
        val list = arrayListOf<DogImagesUrl>()
        apiHelper.getDogImagesUrl(breedName).message.iterator().forEach {
            val dogUrl = DogImagesUrl(it.asString)
            list.add(dogUrl)
        }
        return list
    }
}