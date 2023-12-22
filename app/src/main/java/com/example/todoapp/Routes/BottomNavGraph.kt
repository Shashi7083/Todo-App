package com.example.todoapp.Routes

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.example.todoapp.Screens.BOTTOM_NAVIGATION_ROUTE
import com.example.todoapp.Screens.BottomBarScreens
import com.example.todoapp.Screens.Calendar
import com.example.todoapp.Screens.Home
import com.example.todoapp.Screens.QuickListTaskScreen
import com.example.todoapp.viewModels.SharedDataViewModel


fun NavGraphBuilder.BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    taskViewModel: TaskViewModel,
    sharedDataViewModel: SharedDataViewModel
) {

//    NavHost(
//        navController = navController,
//        startDestination = BottomBarScreens.Home.route
//    )

    navigation(
        startDestination = BottomBarScreens.Home.route,
        route = BOTTOM_NAVIGATION_ROUTE
    ){

        composable(
            route = BottomBarScreens.Home.route
        ){
            Home(modifier = modifier, navController = navController,taskViewModel, sharedDataViewModel)
        }

        composable(
            route = BottomBarScreens.Calendar.route
        ){
            Calendar(modifier = modifier, navController = navController,taskViewModel)
        }

        composable(
            route = BottomBarScreens.QuickListTask.route
        ){
        QuickListTaskScreen(modifier = modifier, navController = navController)
        }

    }
}