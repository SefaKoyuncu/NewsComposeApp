package com.sefa.newsapp.data.datasources.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sefa.newsapp.domain.model.NewsUIModel

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNews(news: NewsUIModel)

    @Query("DELETE FROM news WHERE id = :id AND userEmail = :userEmail")
    suspend fun deleteNews(id: Long, userEmail: String)

    @Query("SELECT EXISTS (SELECT 1 FROM news WHERE id = :id AND userEmail = :userEmail) AS RESULT")
    suspend fun isNewsExist(id: Long, userEmail: String): Boolean

    @Query("SELECT * FROM news WHERE userEmail = :userEmail")
    fun getFavNews(userEmail: String): List<NewsUIModel>
}