package com.example.todoapp.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.RoomDatabase.TaskViewModel

@Composable
fun Notification(
    modifier :
    Modifier, navController : NavHostController,
    taskViewModel: TaskViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Notification Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}