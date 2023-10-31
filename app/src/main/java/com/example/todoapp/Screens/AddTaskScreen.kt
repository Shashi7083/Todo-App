package com.example.todoapp.Screens

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.MainActivity


@Composable
fun AddTaskScreen(
//    navController: NavHostController
) {
    val context = LocalContext.current
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


   // Toast.makeText(context, "add", Toast.LENGTH_SHORT).show()
    Scaffold(
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(45.dp, 15.dp, 15.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "",
                        tint = Color.Green
                    )
                }
            }
        },
    ) {
        val padding = it

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go back",
                    modifier = Modifier
                        .clickable {
//                            navController.navigate(BottomBarScreens.Home.route) {
//                                popUpTo(BottomBarScreens.Home.route) {
//                                    inclusive = true
//                                }
//                            }

                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                        .padding(10.dp)

                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "Add Task", fontSize = 20.sp, fontWeight = FontWeight.Bold
                )

            }
            Spacer(modifier = Modifier.width(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = startTime,
                    onValueChange = {
                        startTime = it
                    },
                    modifier = Modifier.weight(0.2f),
                    label = {
                        Text(text = "From")
                    },
                    trailingIcon = {
                        Icon(imageVector = Icons.Filled.Timer, contentDescription = "")
                    },
                    singleLine = false
                )

                Divider(
                    modifier = Modifier.weight(0.1f), color = Color.Green
                )

                OutlinedTextField(
                    value = endTime,
                    onValueChange = {
                        endTime = it
                    },
                    modifier = Modifier.weight(0.2f), label = {
                        Text(text = "To")
                    },
                    trailingIcon = {
                        Icon(imageVector = Icons.Filled.Timer, contentDescription = "")
                    })
            }
            //Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                }, modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(), label = {
                    Text(text = "Title")
                }
            )

            TextField(
                value = description,
                onValueChange = {
                    description = it
                }, modifier = Modifier
                    .padding(15.dp, 15.dp, 15.dp, 15.dp)
                    .fillMaxWidth()
                    .fillMaxSize(),
                label = {
                    Text(text = "Description")
                }
            )


        }
    }


}

