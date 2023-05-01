package com.clean.architecture.presentation.navigation

sealed class Screen(val route:String) {
    object Login : Screen("login_screen")
    object Home : Screen("home_screen")
    object NickName : Screen("nickname_screen")

    object SettingGender : Screen("gender_screen")
    object MovieDetails  : Screen("movie_details")
}