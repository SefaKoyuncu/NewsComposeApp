package com.sefa.newsapp.di

import com.sefa.newsapp.data.datasources.local.room.datasource.LocalDataSource
import com.sefa.newsapp.data.datasources.remote.datasource.RemoteDataSource
import com.sefa.newsapp.data.repository.NewsRepositoryImpl
import com.sefa.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule
{
    @Singleton
    @Provides
    fun provideNewsRepository(
       remoteDataSource: RemoteDataSource,
       localDataSource: LocalDataSource
    ) : NewsRepository {
        return NewsRepositoryImpl(remoteDataSource, localDataSource)
    }
}