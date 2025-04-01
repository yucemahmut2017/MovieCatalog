package com.myuce.moviescatalogs

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.moviescatalog.core.util.ThemePreferenceManager
import com.moviescatalog.features.navigation.MainScreen
import com.myuce.moviescatalogs.ui.theme.MoviesCatalogTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesCatalogTheme {
                MoviesApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesApp() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val cinemaModeFlow = remember { ThemePreferenceManager.getCinemaModeFlow(context) }
    val isCinemaMode by cinemaModeFlow.collectAsState(initial = false)

    val systemUiController = rememberSystemUiController()

    val updatedColor by rememberUpdatedState(
        if (isCinemaMode) Color(0xFF121212) else Color.White
    )

    LaunchedEffect(isCinemaMode) {
        systemUiController.setStatusBarColor(
            color = updatedColor,
            darkIcons = !isCinemaMode
        )
        systemUiController.setNavigationBarColor(
            color = updatedColor,
            darkIcons = !isCinemaMode,
            navigationBarContrastEnforced = false
        )
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    MoviesCatalogTheme(darkTheme = isCinemaMode) {
        Scaffold(
            topBar = {
                if (!isLandscape) {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = stringResource(id = R.string.app_name),
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        actions = {
                            IconButton(onClick = {
                                scope.launch {
                                    ThemePreferenceManager.saveCinemaMode(context, !isCinemaMode)
                                }
                            }) {
                                Icon(
                                    imageVector = if (isCinemaMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
        ) { padding ->
            MainScreen(padding)
        }
    }}