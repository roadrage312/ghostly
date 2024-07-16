package com.ghostly.android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ghostly.android.home.HomeScreen
import com.ghostly.android.login.LoginScreenWithAPIKey
import com.ghostly.android.login.LoginViewModel
import com.ghostly.android.posts.models.Post
import com.ghostly.android.posts.ui.PostDetailScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    data object Posts : Screen("posts", Icons.AutoMirrored.Default.List, "Posts")
    data object Pages : Screen("pages", Icons.Default.Pages, "Pages")
    data object Settings : Screen("settings", Icons.Default.Settings, "Settings")
}

sealed class Destination {
    @Serializable
    data object Home

    @Serializable
    data object Login
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel = koinViewModel(),
) {
    NavHost(navController = navController, startDestination = Destination.Login) {
        composable<Destination.Login> {
            LoginScreenWithAPIKey(
                loginViewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Destination.Home) {
                        popUpTo(Destination.Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Post>(
            typeMap = Post.typeMap
        ) { backStackEntry ->
            val post = backStackEntry.toRoute<Post>()
            PostDetailScreen(
                navController = navController,
                post = post,
            )
        }
        composable<Destination.Home> {
            HomeScreen {
                navController.navigate(it)
            }
        }
    }
}