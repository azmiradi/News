package com.azmiradi.news.utils

import androidx.datastore.preferences.core.booleanPreferencesKey


object Constants {
    const val SUCCESS_CODE=200
    const val OK="ok"
    val DARK_SETTING= booleanPreferencesKey("dark_setting")
    const val DATASTORE_NAME="news_app"

}
enum class NewsSections{
    TopBanner, LatestNews
}
