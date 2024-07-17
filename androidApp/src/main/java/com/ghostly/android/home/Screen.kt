package com.ghostly.android.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    data object Posts : Screen("posts", Icons.AutoMirrored.Default.List, "Posts")
    data object Settings : Screen("settings", Icons.Default.Settings, "Settings")
}
