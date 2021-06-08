package com.salam.getchip.data

import com.salam.getchip.api.ApiHelper
import com.salam.getchip.api.RetrofitBuilder
import javax.inject.Inject

class DogsListRepository(private val apiHelper: ApiHelper)  {
    suspend fun getDogsStringList(): List<DogNames>{
        val list = arrayListOf<DogNames>()
        apiHelper.getDogsList().message.keySet().iterator().forEach {
            val dogName = DogNames(it)
            list.add(dogName)
        }
        return list
    }
}