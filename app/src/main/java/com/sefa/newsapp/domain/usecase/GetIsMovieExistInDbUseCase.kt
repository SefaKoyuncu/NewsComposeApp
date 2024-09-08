package com.sefa.newsapp.domain.usecase

import com.sefa.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetIsMovieExistInDbUseCase @Inject constructor(private val repo: NewsRepository)
{
    suspend fun invoke(newsId: Long) : Flow<Boolean>
            = repo.isNewsExist(newsId)
        .flowOn(Dispatchers.IO)
}

