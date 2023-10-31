package com.example.todoapp.Routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todoapp.Screens.BOTTOM_NAVIGATION_ROUTE
import com.example.todoapp.Screens.ROOT_ROUTE

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = BOTTOM_NAVIGATION_ROUTE,
        route = ROOT_ROUTE
    ) {
        BottomNavGraph(navController = navController, modifier = modifier)
        FullScreenGraph(navController = navController)
    }
}