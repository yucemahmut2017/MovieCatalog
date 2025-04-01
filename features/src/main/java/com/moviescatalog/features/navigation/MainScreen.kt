package com.moviescatalog.features.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.moviescatalog.features.presentation.MovieDetailScreen
import com.moviescatalog.features.presentation.MovieListScreen
import com.moviescatalog.features.presentation.PlayerScreen

@Composable
fun MainScreen(padding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "movie_list") {
        composable("movie_list") {
            MovieListScreen(navController = navController, padding)
        }
        composable("movie_detail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: 0
            MovieDetailScreen(
                movieId = movieId,
                navController = navController,
                paddingValues = padding
            )
        }
        composable(
            route = "player/{title}/{desc}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                },
                navArgument("desc") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val desc = backStackEntry.arguments?.getString("desc") ?: ""
            PlayerScreen(
                movieTitle = title,
                movieDescription = desc,
                navController = navController,
                padding = padding
            )
        }

    }
}
