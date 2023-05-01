package com.clean.architecture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clean.architecture.presentation.view.login.HomeScreen
import com.clean.architecture.presentation.view.on_boarding.GenderScreen
import com.clean.architecture.presentation.view.on_boarding.NickNameScreen

@Composable
fun navGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(route = Screen.Login.route) {
            HomeScreen(navController = navController, homeViewModel = hiltViewModel())
        }

        composable(route = Screen.NickName.route) {entry->
            NickNameScreen(navController = navController, onBoardingViewModel = hiltViewModel(entry))
        }

        composable(route = Screen.SettingGender.route) {entry->
            GenderScreen(navController = navController, onBoardingViewModel = hiltViewModel(entry))
        }
    }
}