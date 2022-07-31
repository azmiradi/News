package com.azmiradi.news.domain.use_cases

import com.azmiradi.news.data.model.Article
import com.azmiradi.news.data.repository.NewsRepository
import com.azmiradi.news.utils.Resource
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class GetNewsUseCase(private val repository: NewsRepository) {
    operator fun invoke()
            : Flow<Resource<List<Article?>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getNews()

            response.body()?.let { it ->
                it.errorMessage?.let {
                    emit(Resource.Error(it))
                    return@flow
                }
                it.articles?.let {
                    emit(Resource.Success(it))
                    return@flow
                }
                emit(Resource.Error<List<Article?>>("Not Found News"))
            }
            emit(
                Resource.Error<List<Article?>>(
                    response.errorBody()?.string()
                        ?: "Something went wrong please try again later!"
                )
            )
        } catch (http: HttpException) {
            emit(
                Resource.Error(
                    http.localizedMessage ?: "Something went wrong please try again later!"
                )
            )
        } catch (io: IOException) {
            emit(Resource.Error("Please check your internet connection and try again later."))
        }
    }.flowOn(Dispatchers.IO)
}