package com.azmiradi.news.presentation.fragment.bookmark

import androidx.lifecycle.*
import com.azmiradi.news.data.model.Article
import com.azmiradi.news.domain.use_cases.DeleteArticleUseCase
import com.azmiradi.news.domain.use_cases.GetLocalArticlesUseCase
import com.azmiradi.news.domain.use_cases.GetNewsUseCase
import com.azmiradi.news.utils.DataState
import com.azmiradi.news.utils.NewsSections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getLocalArticlesUseCase:
    GetLocalArticlesUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
) :
    ViewModel() {

    private var job: Job? = null
    private val _allNewsState = MutableLiveData<List<Article>>(ArrayList())
    val allNewsState: LiveData<List<Article>> = _allNewsState


    private val _deleteArticle = MutableLiveData<Boolean>()
    val deleteArticle: LiveData<Boolean> = _deleteArticle

    fun geBookmark() {
        job?.cancel()
        job = getLocalArticlesUseCase().onEach {
            _allNewsState.value = it
        }.launchIn(viewModelScope)
    }
    fun deleteArticle(article: Article){
        job?.cancel()
        job= deleteArticleUseCase(article).onEach {
            _deleteArticle.value=it
        }.launchIn(viewModelScope)
    }

}