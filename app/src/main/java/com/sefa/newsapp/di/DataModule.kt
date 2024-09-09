package com.sefa.newsapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.sefa.newsapp.data.datasources.local.room.NewsDAO
import com.sefa.newsapp.data.datasources.local.room.NewsDatabase
import com.sefa.newsapp.data.datasources.local.room.datasource.LocalDataSource
import com.sefa.newsapp.data.datasources.remote.datasource.RemoteDataSource
import com.sefa.newsapp.data.datasources.remote.service.NewsService
import com.sefa.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.Cache
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
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, context: Context): OkHttpClient
    {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(Cache(context.cacheDir, 10 * 1024 * 1024)) // 10 MB cache
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
        database.newsDao()

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

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {context.preferencesDataStoreFile("user_preferences") }
        )
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object FirebaseModule {

        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}