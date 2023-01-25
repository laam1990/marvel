package com.example.marvel.repository

import com.example.marvel.data.api.RemoteDataSource
import com.example.marvel.data.model.BaseResponseData
import com.example.marvel.data.util.Resource
import com.example.marvel.data.util.networkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MarvelRepository {

    override fun getCharacters(name: String): Flow<Resource<BaseResponseData>> =
        networkResource(
            networkCall = {
                remoteDataSource.getCharacters(name)
            }
        )
}