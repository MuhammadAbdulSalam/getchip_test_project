package com.salam.getchip.data

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

/**
 * @author Muhammad Abdul Salam
 */

data class DogsImageUrlsResponse (
    @SerializedName("message")
    val message: JsonArray,
    @SerializedName("status")
    val status: String,
)