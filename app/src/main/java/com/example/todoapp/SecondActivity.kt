package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.Model.tasks
import com.example.todoapp.RoomDatabase.TaskViewModel

import com.example.todoapp.Screens.CreateTask
import com.example.todoapp.Screens.TaskDetailScreen
import com.example.todoapp.ui.theme.TodoAppTheme

class SecondActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme

                val taskViewModel : TaskViewModel = viewModel()

                var task : tasks
                val details = intent.getIntExtra("details",0)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if(details ==1){
                        task = intent.getSerializableExtra("task_data") as tasks
                        TaskDetailScreen(
                            task = task,
                            taskViewModel = taskViewModel
                        )
                    }
                    else{
                        if(details == 2)
                        CreateTask(
                            taskViewModel
                        )
                    }

                }
            }
        }
    }
}


