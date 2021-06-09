package com.salam.getchip.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/**
 * @author Muhammad Abdul Salam
 */

data class DogListResponse (
    @SerializedName("message")
    val message: JsonObject,
    @SerializedName("status")
    val status: String,
)