package com.ghostly.android.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ghostly.android.Screen
import com.ghostly.android.posts.PostsScreen

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val context = LocalContext.current
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf<Screen>(Screen.Posts) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Screen.Posts.icon,
                            contentDescription = Screen.Posts.title
                        )
                    },
                    label = { Text(Screen.Posts.title) },
                    selected = selectedTab == Screen.Posts,
                    onClick = {
                        selectedTab = Screen.Posts
                        navController.navigate(Screen.Posts.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Screen.Pages.icon,
                            contentDescription = Screen.Pages.title
                        )
                    },
                    label = { Text(Screen.Pages.title) },
                    selected = selectedTab == Screen.Pages,
                    onClick = {
                        selectedTab = Screen.Pages
                        navController.navigate(Screen.Pages.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = Screen.Settings.icon,
                            contentDescription = Screen.Settings.title
                        )
                    },
                    label = { Text(Screen.Settings.title) },
                    selected = selectedTab == Screen.Settings,
                    onClick = {
                        selectedTab = Screen.Settings
                        navController.navigate(Screen.Settings.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Posts.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Posts.route) { PostsScreen(mainNavController) }
            composable(Screen.Pages.route) { PagesScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }

    BackHandler {
        (context as? Activity)?.finish()
    }
}