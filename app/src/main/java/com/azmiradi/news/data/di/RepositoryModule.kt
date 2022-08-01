package com.azmiradi.news.data.di

import com.azmiradi.news.data.data_source.datasource.NewsLocalDataSource
import com.azmiradi.news.data.data_source.datasource.NewsRemoteDataSource
import com.azmiradi.news.data.repository.NewsRepository
import com.azmiradi.news.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource,
        localDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource, localDataSource)
    }

}