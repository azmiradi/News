package com.azmiradi.news.presentation.ui_handlers

interface HandlerRequest : Handlers {
    fun startRequest()
    fun endRequest(errorMessage: String? = null)
}