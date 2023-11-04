package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.Components.ProfileHeader
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.example.todoapp.Screens.BottomBarScreens
import com.example.todoapp.Routes.BottomNavGraph
import com.example.todoapp.Routes.NavGraph
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val taskViewModel : TaskViewModel = viewModel()

            val navController = rememberNavController()
            var selectedScreen by remember { mutableStateOf(1) }

            val currentRoute = navController.currentDestination?.route
            val showBottomNavigation = currentRoute != BottomBarScreens.AddTask.route

            val screens = listOf(
                BottomBarScreens.Calendar,
                BottomBarScreens.Home,
                BottomBarScreens.Notification
            )


            var isTopBarVisible by remember { mutableStateOf(false) }

            TodoAppTheme {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            backgroundColor = Color.White
                        ) {
                            Box(
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 0.dp,
                                    bottom = 4.dp
                                )
                            ) {
                                ProfileHeader(navController){
                                    if(it ==1){
                                        selectedScreen = 2
                                    }
                                }
                            }

                        }
                    },
                    bottomBar = {


                        BottomNavigation(
                            modifier = Modifier.height(73.dp),
                            backgroundColor = Color.White,
                            elevation = 0.dp
                        ) {
                            screens.forEachIndexed { index, item ->

                                val icon: ImageVector = when (index) {
                                    0 -> Icons.Filled.CalendarMonth
                                    1 -> Icons.Filled.Home
                                    2 -> Icons.Filled.Notifications
                                    else -> Icons.Filled.Home
                                }

                                BottomNavigationItem(
                                    selected = selectedScreen == index,
                                    onClick = {
                                        selectedScreen = index
                                        navController.navigate(item.route)

                                        isTopBarVisible = index != 0

                                    },
                                    icon = {
                                        Box(
                                            modifier = Modifier
                                                .size(80.dp)
                                                .clip(CircleShape)
                                                .background(if (selectedScreen == index) Color.Black else Color.White),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = icon,
                                                contentDescription = "Screen",
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .padding(12.dp),
                                                tint = if (selectedScreen == index) Color.White else Color.Black
                                            )
                                        }
                                    })
                            }
                        }

                    }
                ) {
//                    BottomNavGraph(
//                        navController = navController, modifier = Modifier.padding(it))
                    NavGraph(navController = navController, modifier = Modifier.padding(it),taskViewModel)
                }

            }
        }
    }
}


