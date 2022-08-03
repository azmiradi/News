package com.azmiradi.news.presentation.fragment.home

import androidx.lifecycle.*
import com.azmiradi.news.data.model.Article
import com.azmiradi.news.domain.use_cases.GetLocalArticlesUseCase
import com.azmiradi.news.domain.use_cases.GetNewsUseCase
import com.azmiradi.news.domain.use_cases.SaveArticleUseCase
import com.azmiradi.news.utils.DataState
import com.azmiradi.news.utils.NewsSections
import com.azmiradi.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val saveArticlesUseCase: SaveArticleUseCase
) : ViewModel() {

    private var job: Job? = null
    private val _allNewsState = MutableLiveData(DataState<Map<String, List<Article>?>>())
    val allNewsState: LiveData<DataState<Map<String, List<Article>?>>> = _allNewsState

    fun getNews() {
        _allNewsState.value = DataState(true)
        val responseDataMap = HashMap<String, List<Article>?>()
        val allEgyptNews = getNewsUseCase(country = "eg")
        val bBCNewsNextWebNews = getNewsUseCase(sources = "bbc-news,the-next-web")

        job?.cancel()
        job = combine(
            allEgyptNews, bBCNewsNextWebNews
        )
        { egyptNews, otherNews ->
            responseDataMap[NewsSections.TopBanner.name] = egyptNews?.filterNotNull()
            responseDataMap[NewsSections.LatestNews.name] = otherNews?.filterNotNull()
            _allNewsState.value = DataState(data = responseDataMap)
        }.catch { error ->
            _allNewsState.value = DataState(error = error.message.toString())
        }.launchIn(viewModelScope)
    }

}