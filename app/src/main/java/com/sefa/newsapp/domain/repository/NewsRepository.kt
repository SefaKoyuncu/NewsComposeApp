package com.sefa.newsapp.domain.repository

import com.sefa.newsapp.domain.model.NewsUIModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository
{
    suspend fun getNewsFromApi(): Flow<List<NewsUIModel>>

    suspend fun insertNews(news: NewsUIModel)

    suspend fun deleteNews(newsId: Long)

    suspend fun isNewsExist(newsId: Long) : Flow<Boolean>

    suspend fun getFavNews(): Flow<List<NewsUIModel>>
}