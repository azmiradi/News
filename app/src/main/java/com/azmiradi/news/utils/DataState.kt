package com.azmiradi.news.utils

data class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = "",
 )
