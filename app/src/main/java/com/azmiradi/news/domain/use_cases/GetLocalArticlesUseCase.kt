package com.azmiradi.news.domain.use_cases

import com.azmiradi.news.data.model.Article
import com.azmiradi.news.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLocalArticlesUseCase @Inject constructor (private val repository: NewsRepository) {
    operator fun invoke() = flow<List<Article>> {
        repository.getLocalArticle().collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)
}