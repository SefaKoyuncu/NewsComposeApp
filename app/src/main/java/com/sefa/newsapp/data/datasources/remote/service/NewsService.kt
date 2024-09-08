package com.sefa.newsapp.data.datasources.remote.service

import com.sefa.newsapp.data.model.NewsResponse
import com.sefa.newsapp.utils.Constants.API_KEY
import com.sefa.newsapp.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET(END_POINT)
    suspend fun getMostPopularNews(
        @Query("api-key") apiKey: String = API_KEY
    ) : Response<NewsResponse>
}