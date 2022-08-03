package com.azmiradi.news.data.network

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServicesTest {

    private lateinit var service: ApiServices
    private lateinit var server: MockWebServer

    private val sources: String = "the-next-web,bbc-news"
    private val fileName = "newsapi.json"

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }

    private fun enqueueMockResponse() {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @Test
    fun getBBCNextWebNewsFromAPITest() {
        runBlocking {
            enqueueMockResponse()
            val responseBody = service.getNews(sources = sources).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?sources=the-next-web%2Cbbc-news&apiKey=c045de1344e24899bffd2bb6d9c08bd5")
        }
    }

    @Test
    fun getArticlesSizeTest() {
        runBlocking {
            enqueueMockResponse()
            val responseBody = service.getNews(sources = sources).body()
            val articlesList = responseBody!!.articles
            assertThat(articlesList!!.size).isEqualTo(19)
        }
    }

    @Test
    fun getArticlesCorrectContent() {
        runBlocking {
            enqueueMockResponse()
            val responseBody = service.getNews(sources = sources).body()
            val articlesList = responseBody!!.articles
            val articlesItem = articlesList!![0]
            assertThat(articlesItem!!.url).isEqualTo("http://www.bbc.co.uk/sport/football/62404090")
            assertThat(articlesItem.title).isEqualTo("Argentina, Chile, Uruguay & Paraguay in World Cup bid")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}