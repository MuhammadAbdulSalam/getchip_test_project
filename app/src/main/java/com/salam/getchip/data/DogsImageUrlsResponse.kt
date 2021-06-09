package com.salam.getchip.data

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class DogsImageUrlsResponse (
    @SerializedName("message")
    val message: JsonArray,
    @SerializedName("status")
    val status: String,
)