package com.example.todoapp.Screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todoapp.Components.ClickType
import com.example.todoapp.Components.TaskView
import com.example.todoapp.Components.WelcomeMessage
import com.example.todoapp.Model.taskList
import com.example.todoapp.Model.tasks
import com.example.todoapp.R
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.example.todoapp.SecondActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    modifier : Modifier,
    navController : NavHostController,
    taskViewModel: TaskViewModel
){
    val db_tasks = taskViewModel.tasks.collectAsState(emptyList())
//    Log.d("test","${db_tasks.value} \n\n\n")
//    Log.d("test", "$taskList \n\n\n")
    var delete = remember{ mutableStateOf(false) }
    var deleteTask = remember{ mutableStateOf(taskList.get(0)) }



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

            item {
//                Spacer(modifier = Modifier.height(30.dp))
                WelcomeMessage()
                Spacer(modifier = Modifier.height(30.dp))
            }

            items(db_tasks.value) { task ->
                TaskView(
                    task = task,
                    onClick = { task, clickType ->
                        when (clickType) {
                            ClickType.ViewClick -> {
                                val intent = Intent(context, SecondActivity::class.java)
                                intent.putExtra("details", 1)
                                intent.putExtra("task_data", task)

                                context.startActivity(intent)
                            }

                            ClickType.DeleteClick -> {

                                delete.value = true;
                                deleteTask.value = task


                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


    }

    if(delete.value){
        AlertDialogSetUp(task = deleteTask.value, taskViewModel =taskViewModel , delete)

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
        val intent = Intent(context , SecondActivity::class.java)
        intent.putExtra("details",2)
     context.startActivity(intent)

    }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}


@Composable
fun AlertDialogSetUp(task: tasks, taskViewModel: TaskViewModel, delete: MutableState<Boolean>){
    // of dialog box to open as true.
    var openDialog = remember { mutableStateOf(true) }

    // below line is to check if the
    // dialog box is open or not.
    if (openDialog.value) {
        // below line is use to
        // display a alert dialog.
        AlertDialog(

            // on dialog dismiss we are setting
            // our dialog value to false.
            onDismissRequest = {
                openDialog.value = false
                               delete.value = false},

            // below line is use to display title of our dialog
            // box and we are setting text color to white.
            title = { Text(text = "Delete", color = Color.Red.copy(alpha = 0.6f)) },

            // below line is use to display
            // description to our alert dialog.
            text = { Text("Do you want to Delete task!", color = Color.White) },

            // in below line we are displaying
            // our confirm button.
            confirmButton = {
                // below line we are adding on click
                // listener for our confirm button.
                TextButton(
                    onClick = {
                        openDialog.value = false
                        delete.value = false
                        taskViewModel.deleteTask(task)
                    }
                ) {
                    // in this line we are adding
                    // text for our confirm button.
                    Text("Confirm", color = Color.White)
                }
            },
            // in below line we are displaying
            // our dismiss button.
            dismissButton = {
                // in below line we are displaying
                // our text button
                TextButton(
                    // adding on click listener for this button
                    onClick = {
                        openDialog.value = false
                        delete.value = false

                    }
                ) {
                    // adding text to our button.
                    Text("Dismiss", color = Color.White)
                }
            },
            // below line is use to add background color to our alert dialog
            backgroundColor = colorResource(id = R.color.teal_200),

            // below line is use to add content color for our alert dialog.
            contentColor = Color.White
        )
    }
}