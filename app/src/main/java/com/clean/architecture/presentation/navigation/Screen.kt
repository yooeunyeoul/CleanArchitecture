package com.clean.architecture.presentation.navigation

sealed class Screen(val route:String) {
    object Home : Screen("home_screen")
    object MovieDetails  : Screen("movie_details")
}