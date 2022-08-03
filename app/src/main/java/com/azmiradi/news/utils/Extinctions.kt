package com.azmiradi.news.utils
import androidx.datastore.preferences.preferencesDataStore

import android.app.Application
import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.azmiradi.news.R
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.convertDateFormate(format: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"): String {
    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
    return try {
        val time: Long = sdf.parse(this)?.time ?: 0
        val now = System.currentTimeMillis()
        DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS).toString()
    } catch (e: ParseException) {
        if (format.endsWith("SSS'Z'"))
            this.convertDateFormate("yyyy-MM-dd'T'HH:mm:ss'Z'")
        else
            "unknown"
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

val Application.datastore: DataStore<Preferences>
        by preferencesDataStore(name = Constants.DATASTORE_NAME)