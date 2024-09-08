package com.sefa.newsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sefa.newsapp.data.datasources.local.NewsDAO
import com.sefa.newsapp.data.datasources.local.NewsDatabase
import com.sefa.newsapp.data.datasources.local.datasource.LocalDataSource
import com.sefa.newsapp.data.datasources.remote.datasource.RemoteDataSource
import com.sefa.newsapp.data.datasources.remote.service.NewsService
import com.sefa.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule
{
    @Provides
    fun provideBaseURL() = Constants.BASE_URL

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient
    {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String, okHttpClient: OkHttpClient): NewsService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NewsService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): NewsDatabase
    {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java, "NewsDatabase")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryDao(database: NewsDatabase): NewsDAO =
        database.movieDao()

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: NewsService): RemoteDataSource
        = RemoteDataSource(service)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: NewsDAO): LocalDataSource
            = LocalDataSource(dao)


}