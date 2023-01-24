package com.example.marvel.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Summary(
    @Json(name = "name")
    val name: String?,
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "type")
    val type: String?
)