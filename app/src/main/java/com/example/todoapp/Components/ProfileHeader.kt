package com.example.todoapp.Components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.SettingActivity
import androidx.compose.material.DropdownMenuItem
import androidx.compose.ui.graphics.Color
import com.example.todoapp.ui.theme.Orange
import com.example.todoapp.viewModels.SharedDataViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileHeader(
    navController: NavHostController,
    sharedDataViewModel: SharedDataViewModel
) {

    var showMenu by remember { mutableStateOf(false) }
    var priority = sharedDataViewModel.selectedPriority
    var color =
            when (priority) {
                3 -> Color.Black
                2 -> Color.Green
                1 -> Orange 
                0 -> Color.Red
                else -> Color.Black
            }


    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.todo_logo),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .clickable {

                }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = {
                showMenu = !showMenu
            }) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    contentDescription = "sort",
                    tint = color
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }) {


                DropdownMenuItem(onClick = {
                    sharedDataViewModel.setSelectedPriority(3)
                    showMenu = !showMenu
                }) {
                    Text(text = "All")
                }

                DropdownMenuItem(onClick = {
                    sharedDataViewModel.setSelectedPriority(0)
                    showMenu = !showMenu
                }) {
                    Text(text = "High", color = Color.Red)
                }
                DropdownMenuItem(onClick = {
                    sharedDataViewModel.setSelectedPriority(1)
                    showMenu = !showMenu
                }) {
                    Text(text = "Medium", color = Orange)
                }
                DropdownMenuItem(onClick = {
                    sharedDataViewModel.setSelectedPriority(2)
                    showMenu = !showMenu
                }) {
                    Text(text = "Low", color = Color.Green)
                }


            }



            Spacer(modifier = Modifier.width(8.dp))
//            BadgedBox(
//                badge = {
////                Badge(
////                    containerColor = Orange,
////                    contentColor = Color.White,
////                    modifier = Modifier.offset(y = 6.dp,x = -9.dp)  // Taking this badge down by 7 dp
////
////                )
//                },
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Settings,
//                    contentDescription = "Notification",
//                    modifier = Modifier
//                        .clickable {
////                        navController.navigate(BottomBarScreens.Notification.route){
////                            popUpTo(BottomBarScreens.Notification.route){
////                                inclusive = true
////                            }
////                        }
//                            context.startActivity(Intent(context, SettingActivity::class.java))
//                        }
//                )
//            }
        }



    }
}