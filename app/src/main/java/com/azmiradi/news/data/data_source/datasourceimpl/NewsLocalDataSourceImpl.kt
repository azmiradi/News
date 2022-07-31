package com.azmiradi.news.data.data_source.datasourceimpl

import com.azmiradi.news.data.data_source.datasource.NewsLocalDataSource
import com.azmiradi.news.data.database.NewsDao
import com.azmiradi.news.data.model.Article
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val newsDao: NewsDao
) : NewsLocalDataSource {

    override suspend fun saveArticle(article: Article): Long {
        return newsDao.saveArticle(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getAllArticles()
    }

    override suspend fun deleteArticle(article: Article): Int {
        return newsDao.deleteArticle(article)
    }

}