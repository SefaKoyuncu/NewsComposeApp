package com.sefa.newsapp.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sefa.newsapp.domain.model.NewsUIModel

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNews(news: NewsUIModel) //update and insert

    @Query("DELETE FROM news WHERE id = :id")
    suspend fun deleteNews(id: Long)

    @Query("SELECT EXISTS (SELECT 1 FROM news WHERE id = :id) AS RESULT")
    suspend fun isNewsExist(id: Long) : Boolean

    @Query("SELECT * FROM news")
    fun getFavNews() : List<NewsUIModel>
}