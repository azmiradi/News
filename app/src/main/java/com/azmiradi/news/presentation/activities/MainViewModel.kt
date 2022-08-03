package com.azmiradi.news.presentation.activities

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azmiradi.news.utils.Constants
import com.azmiradi.news.utils.datastore
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(private val application: Application): ViewModel() {
    private val _isDark = MutableLiveData<Boolean>()
    val isDark: LiveData<Boolean> = _isDark

    init {
        getMode()
    }

    fun getMode() {
        application.datastore.data.map { mutablePreference ->
            mutablePreference[Constants.DARK_SETTING]
        }.onEach {
            _isDark.value = it
        }.launchIn(viewModelScope)
    }
}