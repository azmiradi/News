package com.azmiradi.news.data.data_source.datasource

import com.azmiradi.news.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticle(article: Article):Long

    suspend fun deleteArticle(article: Article):Int

    fun getArticles():Flow<List<Article>>
}