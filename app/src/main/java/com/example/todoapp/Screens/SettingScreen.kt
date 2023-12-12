package com.example.todoapp.Screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.MainActivity
import com.example.todoapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {

    val context = LocalContext.current
    var Notificationchecked by remember { mutableStateOf(true) }
    var DarkModeChecked by remember { mutableStateOf(false) }

    var penColor by remember{ mutableStateOf(Color.LightGray) }
    var isEdit by remember { mutableStateOf(false)}

    var name by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable {
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                )



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Settings",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .size(130.dp)
                            .clip(CircleShape)
                            //     .border(border = BorderStroke(1.dp,Color.Red.copy(alpha = 0.5f)),shape = CircleShape)
                            .border(border = BorderStroke(2.dp, Color.Green), shape = CircleShape)
                    )
                }
                TextField(
                    value =name,
                    onValueChange = {
                        if(it.length <=20) {
                            name = it
                        }
                    },
                    enabled = isEdit,
                    modifier  = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                    label = {
                        Text(
                            text = "Name",
                        )
                    },
                    textStyle = TextStyle(fontSize = 18.sp),
                    maxLines = 1,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ModeEdit,
                            contentDescription ="editname" ,
                            tint = penColor,
                            modifier = Modifier.clickable {
                                isEdit = !isEdit
                                if(!isEdit){
                                    penColor = Color.LightGray
                                }else{
                                    penColor = Color.Blue
                                }
                            })
                    }
                )



                Text(
                    text = "Account Settings",
                    fontSize = 16.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 30.dp)
                )




                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = "Push Notification",
                        fontSize = 18.sp
                    )

                    Switch(
                        checked = Notificationchecked,
                        onCheckedChange = {
                            Notificationchecked = it
                        },
                        modifier = Modifier.scale(0.8f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Dark Mode",
                        fontSize = 18.sp
                    )

                    Switch(
                        checked = DarkModeChecked,
                        onCheckedChange = {
                            DarkModeChecked = it
                        },
                        modifier = Modifier.scale(0.8f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clickable {
                            Toast
                                .makeText(context, "no updates available", Toast.LENGTH_SHORT)
                                .show()
                        }
                ) {
                    Text(
                        text = "New Updates",
                        fontSize = 18.sp
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "arrow"
                    )

                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                        .clickable {

                        }
                ) {
                    Text(
                        text = "About us",
                        fontSize = 18.sp
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "arrow"
                    )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                        .clickable {

                        }
                ) {
                    Text(
                        text = "Privacy policy",
                        fontSize = 18.sp
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "arrow"
                    )

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp)
                        .clickable {

                        }
                ) {
                    Text(
                        text = "Terms and conditions",
                        fontSize = 18.sp
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "arrow"
                    )

                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun preview() {
    SettingScreen()
}


