package com.azmiradi.news.presentation.fragment.search

import androidx.lifecycle.*
import com.azmiradi.news.data.model.Article
import com.azmiradi.news.domain.use_cases.DeleteArticleUseCase
import com.azmiradi.news.domain.use_cases.GetLocalArticlesUseCase
import com.azmiradi.news.domain.use_cases.GetNewsUseCase
import com.azmiradi.news.utils.DataState
import com.azmiradi.news.utils.NewsSections
import com.azmiradi.news.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) :
    ViewModel() {

    private var job: Job? = null
    private val _allNewsState = MutableLiveData<DataState<List<Article>>>(DataState())
    val allNewsState: LiveData<DataState<List<Article>>> = _allNewsState


    fun search(keyword: String) {
        _allNewsState.value = DataState(isLoading = true)

        job?.cancel()
        job = getNewsUseCase(querySearch = keyword).onStart {
            delay(1500)
        }.onEach {
            _allNewsState.value = DataState(data = it?.filterNotNull())
        }.catch { error ->
            _allNewsState.value = DataState(error = error.message.toString())
        }.launchIn(viewModelScope)
    }


}