package com.azmiradi.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azmiradi.news.data.model.Article

@Database(entities = [Article::class],version = 1,exportSchema = false)
abstract  class Database : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}