package com.sefa.newsapp.data.datasources.local.room.datasource

import com.sefa.newsapp.data.datasources.local.room.NewsDAO
import com.sefa.newsapp.domain.model.NewsUIModel
import javax.inject.Inject

class LocalDataSource
@Inject
constructor(private val dao: NewsDAO)
{
    suspend fun insertNews(news: NewsUIModel) = dao.upsertNews(news)

    suspend fun deleteNews(newsId: Long, userEmail: String) = dao.deleteNews(newsId,userEmail)

    suspend fun isNewsExist(newsId: Long, userEmail: String) = dao.isNewsExist(newsId,userEmail)

    fun getFavNews(userEmail: String) = dao.getFavNews(userEmail)
}