package com.moviescatalog.core.di

import com.moviescatalog.data.usecase.GetMovieByIdUseCaseImpl
import com.moviescatalog.data.usecase.GetPopularMoviesUseCaseImpl
import com.moviescatalog.data.usecase.GetTopRatedMoviesUseCaseImpl
import com.moviescatalog.data.usecase.GetTopRevenueMoviesUseCaseImpl
import com.moviescatalog.domain.repository.MovieRepository
import com.moviescatalog.domain.usecase.GetMovieByIdUseCase
import com.moviescatalog.domain.usecase.GetPopularMoviesUseCase
import com.moviescatalog.domain.usecase.GetTopRatedMoviesUseCase
import com.moviescatalog.domain.usecase.GetTopRevenueMoviesUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetMovieByIdUseCase(
        repository: MovieRepository
    ): GetMovieByIdUseCase = GetMovieByIdUseCaseImpl(repository)

    @Provides
    fun provideGetPopularMoviesUseCase(
        repository: MovieRepository
    ): GetPopularMoviesUseCase = GetPopularMoviesUseCaseImpl(repository)

    @Provides
    fun provideGetTopRatedMoviesUseCase(
        repository: MovieRepository
    ): GetTopRatedMoviesUseCase = GetTopRatedMoviesUseCaseImpl(repository)

    @Provides
    fun provideGetTopRevenueMoviesUseCase(
        repository: MovieRepository
    ): GetTopRevenueMoviesUseCase = GetTopRevenueMoviesUseCaseImpl(repository)
}

