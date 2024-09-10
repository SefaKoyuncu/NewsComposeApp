package com.sefa.newsapp.domain.usecase

import com.sefa.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(private val repo: NewsRepository)
{
    suspend fun invoke(newsId: Long, userEmail: String)
    {
        repo.deleteNews(newsId,userEmail)
    }
}