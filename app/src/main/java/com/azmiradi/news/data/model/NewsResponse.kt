package com.azmiradi.news.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(

    @SerializedName("totalResults")
    val totalResults: Int? = null,

    @SerializedName("articles")
    val articles: List<Article?>? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("message")
    val errorMessage: String? = null
)


@Entity(tableName = "articles", indices = [Index(value = ["url"], unique = true)])
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("author")
    val author: String? = null,

    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("content")
    val content: String? = null
) : Serializable

