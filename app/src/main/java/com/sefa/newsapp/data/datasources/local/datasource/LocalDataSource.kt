package com.sefa.newsapp.data.datasources.local.datasource

import com.sefa.newsapp.data.datasources.local.NewsDAO
import com.sefa.newsapp.domain.model.NewsUIModel
import javax.inject.Inject

class LocalDataSource
@Inject
constructor(private val dao: NewsDAO)
{
    suspend fun insertNews(news: NewsUIModel) = dao.upsertNews(news)

    suspend fun deleteNews(newsId: Long) = dao.deleteNews(newsId)

    suspend fun isNewsExist(newsId: Long) = dao.isNewsExist(newsId)

    fun getFavNews() = dao.getFavNews()
}