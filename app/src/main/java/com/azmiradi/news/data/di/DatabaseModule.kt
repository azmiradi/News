package com.azmiradi.news.data.di

import android.app.Application
import androidx.room.Room
import com.azmiradi.news.data.database.Database
import com.azmiradi.news.data.database.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNews(database: Database): NewsDao {
        return database.getNewsDao()
    }


}