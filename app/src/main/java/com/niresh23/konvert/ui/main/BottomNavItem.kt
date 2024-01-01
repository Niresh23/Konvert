package com.niresh23.konvert.ui.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.niresh23.konvert.R

sealed class BottomNavItem(val icon: ImageVector, @StringRes val description: Int, val route: String) {
    object Home: BottomNavItem(icon = Icons.Rounded.Home, R.string.home_content_description, "home")
    object Favorite: BottomNavItem(icon = Icons.Rounded.Favorite, R.string.favorite_content_description, "favorite")
    object  Chart: BottomNavItem(icon = Icons.Rounded.BarChart, R.string.chart_content_decription, "chart")
}