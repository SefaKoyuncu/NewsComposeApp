package com.sefa.newsapp.domain.usecase

import android.util.Log
import androidx.annotation.ReturnThis
import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.domain.repository.NewsRepository
import com.sefa.newsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(private val repo: NewsRepository)
{
    fun invoke(): Flow<Resource<List<NewsUIModel>>>
    {
        return flow {
            emit(Resource.Loading())
            try {
                repo.getNewsFromApi()
                    .collect{list->
                        emit(Resource.Success(list))
                        Log.e("TAG",list.size.toString())
                    }
            } catch (e: Exception) {
                Log.e("TAG",e.localizedMessage ?: "An error occurred :/")
                emit(Resource.Error(e.localizedMessage ?: "An error occurred :/"))
            }
        }.flowOn(Dispatchers.IO)
    }
}



