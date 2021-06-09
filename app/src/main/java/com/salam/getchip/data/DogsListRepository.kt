package com.salam.getchip.data

import com.salam.getchip.api.ApiHelper
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Muhammad Abdul Salam
 */

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

    /**
     * Every time will store 10 random images from the list
     * If limit set to retrofit, It will take first 10 images always
     * To make sure we always show user random 10 images we take those images
     * from random positions
     */
    suspend fun getDogImagesUrl(breedName: String): List<DogImagesUrl>{
        val list = arrayListOf<DogImagesUrl>()
        val resultSize = apiHelper.getDogImagesUrl(breedName).message.size()
        if(resultSize== 0)
        {
            return list
        }
        if(resultSize<= 10)
        {
            apiHelper.getDogImagesUrl(breedName).message.iterator().forEach {
                val dogUrl = DogImagesUrl(it.asString)
                list.add(dogUrl)
            }
        }
        else
        {
            val min = 0
            val max = resultSize - 10
            val randomPosition = Random().nextInt(max - min + 1) + min
            for (i in randomPosition..randomPosition+9)
            {
                val dogUrl = DogImagesUrl(apiHelper.getDogImagesUrl(breedName).message.get(i).asString)
                list.add(dogUrl)
            }
        }
        return list
    }
}