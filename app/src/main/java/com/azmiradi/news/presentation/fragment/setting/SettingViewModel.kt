package com.azmiradi.news.presentation.fragment.setting

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.*
import com.azmiradi.news.data.model.Article
import com.azmiradi.news.domain.use_cases.DeleteArticleUseCase
import com.azmiradi.news.domain.use_cases.GetLocalArticlesUseCase
import com.azmiradi.news.domain.use_cases.GetNewsUseCase
import com.azmiradi.news.presentation.activities.MainViewModel
import com.azmiradi.news.utils.Constants.DARK_SETTING
import com.azmiradi.news.utils.DataState
import com.azmiradi.news.utils.NewsSections
import com.azmiradi.news.utils.Resource
import com.azmiradi.news.utils.datastore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val application: Application) :
    MainViewModel(application) {
    private var job: Job? = null

    init {
        getMode()
    }

    fun saveMode(isDark: Boolean) {
        job?.cancel()
        job = viewModelScope.launch {
            application.datastore.edit { mutablePreferences ->
                mutablePreferences[DARK_SETTING] = isDark
            }
        }
    }


}