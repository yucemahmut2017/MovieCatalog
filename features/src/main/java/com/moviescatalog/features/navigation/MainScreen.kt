package com.moviescatalog.features.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviescatalog.features.presentation.MovieDetailScreen
import com.moviescatalog.features.presentation.MovieListScreen

@Composable
fun MainScreen(padding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "movie_list") {
        composable("movie_list") {
            MovieListScreen(navController = navController)
        }
        composable("movie_detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: 0
            MovieDetailScreen(movieId = movieId)
        }
    }
}
