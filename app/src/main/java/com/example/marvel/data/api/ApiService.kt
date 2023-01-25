package com.example.marvel.data.api

import com.example.marvel.data.model.BaseResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("nameStartsWith") name: String,
        @Query("orderBy") orderBy: String = "name",
        @Query("limit") limit: String = "20",
        @Query("ts") ts: String = "1",
        @Query("apikey") apiKey: String = "883bea04c7196768d7e50351f8ac4a08",
        @Query("hash") hash: String = "d3af1165b982fea68c94512d3e726ce8"
    ): BaseResponseData

}