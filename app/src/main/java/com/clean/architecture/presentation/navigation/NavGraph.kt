package com.clean.architecture.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.clean.architecture.presentation.view.home.HomeScreen
import com.clean.architecture.presentation.view.home.HomeViewModel

@Composable
fun navGraph(navController: NavHostController) {
    val homeViewModel : HomeViewModel = hiltViewModel()
    val dd by homeViewModel.state.collectAsState()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, homeViewModel = hiltViewModel())
        }
    }
}