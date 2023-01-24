package com.example.marvel.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stories(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val storySummaries: List<Summary>?,
    @Json(name = "returned")
    val returned: Int?
)