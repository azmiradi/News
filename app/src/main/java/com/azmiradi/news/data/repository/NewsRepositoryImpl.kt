package com.azmiradi.news.data.repository

import com.azmiradi.news.data.data_source.datasource.NewsLocalDataSource
import com.azmiradi.news.data.data_source.datasource.NewsRemoteDataSource
import com.azmiradi.news.data.model.Article
import com.azmiradi.news.data.model.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun getNews(
        querySearch: String?,
        country: String?,
        sources: String?
    ): Response<NewsResponse> {
        return remoteDataSource.getNews(querySearch, country, sources)
    }

    override fun getLocalArticle(): Flow<List<Article>> {
        return localDataSource.getArticles()
    }

    override suspend fun deleteArticle(article: Article): Int {
        return localDataSource.deleteArticle(article)

    }

    override suspend fun saveArticle(article: Article): Long {
        return localDataSource.saveArticle(article)
    }

}