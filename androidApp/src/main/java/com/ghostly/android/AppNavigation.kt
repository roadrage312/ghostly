package com.ghostly.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ghostly.android.home.HomeScreen
import com.ghostly.android.login.ui.LoginScreen
import com.ghostly.android.login.LoginViewModel
import com.ghostly.android.posts.models.Post
import com.ghostly.android.posts.ui.PostDetailScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

object Destination {

    @Serializable
    data class Home(val something: Boolean = false)

    @Serializable
    data class Login(val userLoggedOut: Boolean = false)
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel = koinViewModel(),
) {
    NavHost(navController = navController, startDestination = Destination.Login()) {
        composable<Destination.Login> { backStackEntry ->
            val login = backStackEntry.toRoute<Destination.Login>()
            LoginScreen(
                loginViewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Destination.Home()) {
                        popUpTo(Destination.Login()) {
                            inclusive = true
                        }
                    }
                },
                login.userLoggedOut
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
            HomeScreen(
                onPostClick = { navController.navigate(it) },
                onLogout = {
                    navController.navigate(Destination.Login(true)) {
                        popUpTo(Destination.Home()) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}