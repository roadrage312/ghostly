package com.ghostly.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") {
            MainScreen()
        }
        composable("screen2") {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White))
        }
        composable("screen3") {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Green))
        }
    }
}