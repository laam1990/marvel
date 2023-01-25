package com.example.marvel.repository

import com.example.marvel.data.model.BaseResponseData
import com.example.marvel.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    fun getCharacters(name: String) : Flow<Resource<BaseResponseData>>
}