package com.moviescatalog.features.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.model.MovieCategory
import com.moviescatalog.features.viewmodel.MovieListViewModel

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val moviesByCategory = viewModel.moviesByCategory

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(MovieCategory.entries) { category ->
            val movies by moviesByCategory[category]?.collectAsState() ?: remember { mutableStateOf(emptyList()) }

            MovieSection(
                title = stringResource(id = category.titleRes),
                movies = movies,
                navController = navController)
        }
    }
}





@Composable
fun MovieSection(title: String, movies: List<Movie>, navController: NavController) {
    Column {
        Text(title, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(movies) { movie ->
                MovieCard(movie, navController)
            }
        }
    }
}
@Composable
fun MovieCard(movie: Movie, navController: NavController) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .clickable {
                navController.navigate("movie_detail/${movie.id}")
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.posterUrl),
            contentDescription = movie.title,
            modifier = Modifier
                .height(225.dp)
                .fillMaxWidth()
        )
    }
}