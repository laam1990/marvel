package com.example.marvel.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "offset")
    val offset: Int?,
    @Json(name = "results")
    val characters: List<Character>?,
    @Json(name = "total")
    val total: Int?
)