package com.example.todoapp.Routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.example.todoapp.Screens.BOTTOM_NAVIGATION_ROUTE
import com.example.todoapp.Screens.ROOT_ROUTE
import com.example.todoapp.viewModels.SharedDataViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier,
    taskViewModel: TaskViewModel,
    sharedDataViewModel: SharedDataViewModel
) {

    NavHost(
        navController = navController,
        startDestination = BOTTOM_NAVIGATION_ROUTE,
        route = ROOT_ROUTE
    ) {
        BottomNavGraph(navController = navController, modifier = modifier,taskViewModel, sharedDataViewModel)
        FullScreenGraph(navController = navController,taskViewModel)
    }
}