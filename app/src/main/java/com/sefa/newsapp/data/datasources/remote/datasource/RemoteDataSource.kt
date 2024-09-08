package com.sefa.newsapp.data.datasources.remote.datasource

import com.sefa.newsapp.data.datasources.remote.service.NewsService
import javax.inject.Inject

class RemoteDataSource
@Inject
constructor(private val apiService: NewsService)
{
    suspend fun getNewsFromApi()=apiService.getMostPopularNews()
}