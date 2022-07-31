package com.azmiradi.news.domain.use_cases

import com.azmiradi.news.data.model.Article
import com.azmiradi.news.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DeleteArticleUseCase(private val repository: NewsRepository) {
    operator fun invoke(article: Article) = flow<Boolean> {
        val deletedArticleID = repository.deleteArticle(article)
        if (deletedArticleID != 0)
            emit(true)
        else
            emit(false)
    }.flowOn(Dispatchers.IO)
}