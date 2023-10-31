package com.example.todoapp.Screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todoapp.Components.ProfileHeader
import com.example.todoapp.Components.TaskView
import com.example.todoapp.Components.WelcomeMessage
import com.example.todoapp.Model.taskList
import com.example.todoapp.SecondActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home( modifier : Modifier, navController : NavHostController){

    var addTask = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
                fabAdd(addTask,navController,context)
        },
        modifier = modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
            modifier = Modifier.padding(it)
        ) {
//            item {
//                ProfileHeader()
//            }
            item {
//                Spacer(modifier = Modifier.height(30.dp))
                WelcomeMessage()
                Spacer(modifier = Modifier.height(30.dp))
            }

            items(taskList) { task ->
                TaskView(task = task)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}

@Composable
fun fabAdd(
    addTask: MutableState<Boolean>,
    navController: NavHostController,
    context: Context
) {
    FloatingActionButton(onClick = {
        addTask.value = true
//        navController.navigate(BottomBarScreens.AddTask.route){
//            popUpTo(BottomBarScreens.AddTask.route){
//                inclusive = true
//            }
//        }

     context.startActivity(Intent(context,SecondActivity::class.java))

    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}