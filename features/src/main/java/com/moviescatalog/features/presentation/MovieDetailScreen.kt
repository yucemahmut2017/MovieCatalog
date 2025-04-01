package com.moviescatalog.features.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.moviescatalog.core.util.DateUtils
import com.moviescatalog.domain.model.Movie
import com.moviescatalog.features.R
import com.moviescatalog.features.viewmodel.MovieDetailViewModel
import java.util.Locale

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
@Composable
private fun MovieDetailContent(
    movie: Movie,
    navController: NavController,
    paddingValues: PaddingValues,
    context: Context // Pass context to check internet connection
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        MovieBackdrop(movie)
        MovieOverview(movie)
        PlayMovieButton(movie, navController, context) // Pass context to PlayMovieButton
    }
}

@Composable
private fun MovieBackdrop(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.backdropUrl)
                    .crossfade(true)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .error(R.drawable.no_background)
                    .placeholder(R.drawable.no_background)
                    .build()
            ),
            contentDescription = "Movie Backdrop",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        // Movie Title and Rating
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = movie.title.orEmpty(), // Default empty string if title is null
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )

            movie.rating?.takeIf { it > 0 }?.let { rating ->
                RatingRow(rating)
            }
        }

        // Movie Release Date
        movie.releaseDate?.let {
            Text(
                text = DateUtils.formatReleaseDate(it),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 12.dp)
            )
        }
    }
}


@Composable
private fun RatingRow(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color.Yellow
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = String.format(Locale.US, "%.1f", rating),
            color = Color.White
        )
    }
}

@Composable
private fun MovieOverview(movie: Movie) {
    val overview = movie.overview
    if (!overview.isNullOrBlank()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
@Composable
private fun PlayMovieButton(movie: Movie, navController: NavController, context: Context) {
    Spacer(modifier = Modifier.height(8.dp))
    Button(
        onClick = {
            if (isInternetAvailable(context)) {
                val encodedTitle = Uri.encode(movie.title ?: "now")
                val encodedDesc =
                    Uri.encode(movie.overview.takeIf { it?.isNotBlank() == true } ?: "now")
                navController.navigate("player/$encodedTitle/$encodedDesc")
            } else {
                Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(text = "Play Movie")
    }
}

@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
@SuppressLint("DefaultLocale")
@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    navController: NavController,
    paddingValues: PaddingValues,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    when (val movie = uiState) {
        null -> LoadingScreen()
        else -> MovieDetailContent(movie, navController, paddingValues, context)
    }
}
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    return when {
        actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}
