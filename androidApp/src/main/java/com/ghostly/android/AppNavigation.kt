package com.ghostly.android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Pages
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ghostly.android.home.HomeScreen
import com.ghostly.android.login.LoginScreen
import com.ghostly.android.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    data object Posts : Screen("posts", Icons.AutoMirrored.Default.List, "Posts")
    data object Pages : Screen("pages", Icons.Default.Pages, "Pages")
    data object Settings : Screen("settings", Icons.Default.Settings, "Settings")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    NavHost(navController = navController, startDestination = "main") {
        composable("login") {
            LoginScreen(
                loginViewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable("main") {
            HomeScreen(navController)
        }
    }
}