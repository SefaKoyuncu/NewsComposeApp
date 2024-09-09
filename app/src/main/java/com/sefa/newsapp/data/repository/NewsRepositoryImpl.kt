package com.sefa.newsapp.data.repository

import android.util.Log
import com.sefa.newsapp.data.datasources.local.room.datasource.LocalDataSource
import com.sefa.newsapp.data.datasources.remote.datasource.RemoteDataSource
import com.sefa.newsapp.data.mapper.asNewsUIModel
import com.sefa.newsapp.domain.model.NewsUIModel
import com.sefa.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource,
                    private val localDataSource: LocalDataSource
)
    :NewsRepository
{
    override suspend fun getNewsFromApi(): Flow<List<NewsUIModel>> {
        return flow {
            remoteDataSource.getNewsFromApi()?.let {response->
                if (response.isSuccessful)
                {
                    Log.e("TAG","if if if")

                    val result = response.body()
                    result?.let {list->
                        val newsList = list.news.map { it.asNewsUIModel() }
                        emit(newsList)
                        Log.e("TAG",newsList.size.toString())
                    }
                }
                else
                {
                    Log.e("TAG","hata hata hata")
                }
            }
        }
    }

    override suspend fun insertNews(news: NewsUIModel) = localDataSource.insertNews(news)

    override suspend fun deleteNews(newsId: Long) = localDataSource.deleteNews(newsId)

    override suspend fun isNewsExist(newsId: Long): Flow<Boolean> {
        return flow {
           emit(localDataSource.isNewsExist(newsId))
        }
    }

    override suspend fun getFavNews(): Flow<List<NewsUIModel>> {

        return  flow {
            emit(localDataSource.getFavNews())
        }
    }
}