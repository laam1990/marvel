package com.example.marvel.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "extension")
    val extension: String?,
    @Json(name = "path")
    val path: String?
) {
    fun getCompleteUrl(): String {
        return path.plus("/portrait_xlarge.").plus(extension).replace("http", "https")
    }
}