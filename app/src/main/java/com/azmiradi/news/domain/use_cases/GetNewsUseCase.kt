package com.azmiradi.news.domain.use_cases

import com.azmiradi.news.data.model.Article
import com.azmiradi.news.data.repository.NewsRepository
import com.azmiradi.news.utils.Constants.SUCCESS_CODE
import com.azmiradi.news.utils.Resource
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(
        querySearch: String? = null,
        country: String? = null,
        sources: String? = null,
    ): Flow<List<Article?>?> = flow {
        try {
             val response = repository.getNews(querySearch, country, sources)
            if (response.code() == SUCCESS_CODE) {
                response.body()?.let { it ->
                    emit(it.articles)
                    return@flow
                }
                throw Exception("Not Found Data")
            } else {
                response.errorBody()?.let {
                    throw Exception(it.string())
                }
                throw Exception(response.message())
            }

        } catch (http: HttpException) {
            throw Exception(http.message ?: "something is wrong!, try again")

        } catch (io: IOException) {
            throw Exception("No Internet Connection \n ${io.message}" )
        }
    }.flowOn(Dispatchers.IO)
}