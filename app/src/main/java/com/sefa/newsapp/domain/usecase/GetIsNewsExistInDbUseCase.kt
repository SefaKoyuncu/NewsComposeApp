package com.sefa.newsapp.domain.usecase

import com.sefa.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetIsNewsExistInDbUseCase @Inject constructor(private val repo: NewsRepository)
{
    suspend fun invoke(newsId: Long, userEmail: String) : Flow<Boolean>
            = repo.isNewsExist(newsId,userEmail)
        .flowOn(Dispatchers.IO)
}

