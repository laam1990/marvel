package com.example.marvel.di

import com.example.marvel.data.api.RemoteDataSource
import com.example.marvel.repository.MarvelRepository
import com.example.marvel.repository.MarvelRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMarvelRepositoryImpl(
        remoteDataSource: RemoteDataSource
    ) = MarvelRepositoryImpl(
        remoteDataSource
    ) as MarvelRepository

}