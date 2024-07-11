package com.ghostly.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghostly.android.login.LoginScreen
import com.ghostly.android.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    NavHost(navController = navController, startDestination = "login") {
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
            MainScreen()
        }
    }
}