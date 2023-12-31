package com.example.todoapp.Routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.todoapp.Model.taskList
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.example.todoapp.Screens.AddTaskScreen
import com.example.todoapp.Screens.BottomBarScreens
import com.example.todoapp.Screens.CreateTask
import com.example.todoapp.Screens.OTHER_ROUTE
import com.example.todoapp.Screens.TaskDetailScreen


fun NavGraphBuilder.FullScreenGraph(
    navController : NavHostController,
    taskViewModel: TaskViewModel
){
    navigation(
        startDestination = BottomBarScreens.AddTask.route,
        route = OTHER_ROUTE
    ){
        composable(
            route = BottomBarScreens.AddTask.route
        ){
            AddTaskScreen()
        }

        composable(route = BottomBarScreens.CreateTask.route){
            CreateTask(
                taskViewModel
            )
        }

        composable(route = BottomBarScreens.TaskDetailScreen.route){
            TaskDetailScreen(
                task = taskList.get(0),
                taskViewModel = taskViewModel
            )
        }
    }
}