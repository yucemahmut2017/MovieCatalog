package com.moviescatalog.core.di

import com.moviescatalog.domain.usecase.GetMovieByIdUseCase
import com.moviescatalog.domain.usecase.GetMovieByIdUseCaseImpl
import com.moviescatalog.domain.usecase.GetPopularMoviesUseCase
import com.moviescatalog.domain.usecase.GetPopularMoviesUseCaseImpl
import com.moviescatalog.domain.usecase.GetTopRatedMoviesUseCase
import com.moviescatalog.domain.usecase.GetTopRatedMoviesUseCaseImpl
import com.moviescatalog.domain.usecase.GetTopRevenueMoviesUseCase
import com.moviescatalog.domain.usecase.GetTopRevenueMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetPopularMoviesUseCase(
        impl: GetPopularMoviesUseCaseImpl
    ): GetPopularMoviesUseCase

    @Binds
    abstract fun bindGetTopRevenueMoviesUseCase(
        impl: GetTopRevenueMoviesUseCaseImpl
    ): GetTopRevenueMoviesUseCase

    @Binds
    abstract fun bindGetTopRatedMoviesUseCase(
        impl: GetTopRatedMoviesUseCaseImpl
    ): GetTopRatedMoviesUseCase

    @Binds
    abstract fun bindGetMovieByIdUseCase(
        impl: GetMovieByIdUseCaseImpl
    ): GetMovieByIdUseCase
}