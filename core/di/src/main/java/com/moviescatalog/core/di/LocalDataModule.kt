package com.moviescatalog.core.di

import android.content.Context
import androidx.room.Room
import com.moviescatalog.data.local.AppDatabase
import com.moviescatalog.data.local.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movies_app_cache.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(
        appDatabase: AppDatabase
    ): MovieDao {
        return appDatabase.movieDao()
    }
}

