package com.sefa.newsapp.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sefa.newsapp.domain.model.NewsUIModel

@Database(entities = [NewsUIModel::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase()
{
    abstract fun movieDao() : NewsDAO
}