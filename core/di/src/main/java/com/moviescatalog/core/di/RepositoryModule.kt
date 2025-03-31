package com.moviescatalog.core.di

import com.moviescatalog.data.repository.MovieRepositoryImpl
import com.moviescatalog.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository
}
