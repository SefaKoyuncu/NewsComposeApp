package com.sefa.newsapp.domain.usecase

import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class InsertNewsUseCase @Inject constructor(private val repo: NewsRepository)
{
    suspend fun invoke(newsUIModel: NewsUIModel)
    {
       repo.insertNews(newsUIModel)
    }
}