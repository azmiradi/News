package com.azmiradi.news.data.di

import com.azmiradi.news.data.data_source.datasource.NewsLocalDataSource
import com.azmiradi.news.data.data_source.datasource.NewsRemoteDataSource
import com.azmiradi.news.data.data_source.datasourceimpl.NewsLocalDataSourceImpl
import com.azmiradi.news.data.data_source.datasourceimpl.NewsRemoteDataSourceImpl
import com.azmiradi.news.data.database.NewsDao
import com.azmiradi.news.data.network.ApiServices
import com.azmiradi.news.data.repository.NewsRepository
import com.azmiradi.news.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(
        apiServices: ApiServices
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(apiServices)
    }
    @Provides
    @Singleton
    fun provideNewsLocalDataSource(
        newsDao: NewsDao
    ): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(newsDao)
    }

}