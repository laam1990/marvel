package com.example.marvel.data.api

import com.example.marvel.data.model.BaseResponseData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): RemoteDataSource {

    override suspend fun getCharacters(name: String): BaseResponseData =
        apiService.getCharacters(name)
}