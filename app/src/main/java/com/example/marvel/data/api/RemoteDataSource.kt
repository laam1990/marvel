package com.example.marvel.data.api

import com.example.marvel.data.model.BaseResponseData
import retrofit2.http.Query

interface RemoteDataSource {

    suspend fun getCharacters(
        @Query("name") name: String,
    ): BaseResponseData
}