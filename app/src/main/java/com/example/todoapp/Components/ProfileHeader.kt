package com.example.todoapp.Components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todoapp.R
import com.example.todoapp.Screens.BottomBarScreens
import com.example.todoapp.SettingActivity
import com.example.todoapp.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileHeader(
    navController : NavHostController,
    ProfileHeaderItem :(Int) ->Unit
) {

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
        
        BadgedBox(
            badge ={
//                Badge(
//                    containerColor = Orange,
//                    contentColor = Color.White,
//                    modifier = Modifier.offset(y = 6.dp,x = -9.dp)  // Taking this badge down by 7 dp
//
//                )
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Notification",
                modifier = Modifier
                    .clickable {
//                        navController.navigate(BottomBarScreens.Notification.route){
//                            popUpTo(BottomBarScreens.Notification.route){
//                                inclusive = true
//                            }
//                        }
                        context.startActivity(Intent(context,SettingActivity::class.java))
                    }
            )
        }
    }
}