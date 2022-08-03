package com.azmiradi.news.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.azmiradi.news.data.model.Article
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var newsDao: NewsDao
    private lateinit var appDatabase: Database

    private val testArticle = Article(
        id = 1,
        author = "BBC Sport",
        title = "Argentina, Chile, Uruguay & Paraguay in World Cup bid",
        description = "Argentina, Chile, Uruguay and Paraguay submit a joint bid to host the World Cup in 2023.",
        url = "http://www.bbc.co.uk/sport/football/62404090",
        urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/D6C8/production/_117348945_gettyimages-999564000.jpg",
        publishedAt = "2022-08-03T07:22:17.0161107Z",
        content = "Representatives of Conmebol and Argentina, Chile, Uruguay and Paraguay are joining forces to bid for the 2030 World Cup\r\nArgentina, Chile, Uruguay and Paraguay have submitted a joint bid to host the … [+1186 chars]"
    )
    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), Database::class.java
        )
            .build()
        newsDao = appDatabase.getNewsDao()
    }


    @Test
    fun saveArticleTest() = runBlocking {
        newsDao.saveArticle(testArticle)
        val elements = newsDao.getAllArticles().take(1).toList()
        Truth.assertThat(elements[0][0]).isEqualTo(testArticle)
    }

    @Test
    fun deleteArticleTest() = runBlocking {
        newsDao.saveArticle(testArticle)
        newsDao.deleteArticle(testArticle)
        val elements = newsDao.getAllArticles().take(1).toList()
        Truth.assertThat(elements[0]).isEmpty()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }
}