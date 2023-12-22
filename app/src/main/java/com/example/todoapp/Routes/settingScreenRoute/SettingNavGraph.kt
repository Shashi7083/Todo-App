package com.example.todoapp.Routes.settingScreenRoute

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.Screens.HelpScreen
import com.example.todoapp.Screens.SettingScreen

@Composable
fun SettingNavGraph(
    navController : NavHostController,
    modifier:Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = SettingScreens.SettingScreen.route
    ){
        composable(
           route = SettingScreens.SettingScreen.route
        ){
            SettingScreen(navController = navController)
        }

        composable(
            route = SettingScreens.HelpScreen.route
        ){
            HelpScreen(navController = navController)
        }
    }
}