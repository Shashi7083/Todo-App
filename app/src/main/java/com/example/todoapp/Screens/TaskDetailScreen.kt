package com.example.todoapp.Screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.Model.tasks
import com.example.todoapp.ui.theme.LightBlue
import com.example.todoapp.ui.theme.Orange
import com.example.todoapp.ui.theme.priority2
import com.example.todoapp.ui.theme.unselectedDate


@Composable
fun TaskDetailScreen(
    task : tasks
) {
    val context = LocalContext.current

    var title by remember { mutableStateOf("${task.title}") }
    var isEdit by remember { mutableStateOf(false) }
    var date by remember { mutableStateOf("31 Tuesday, 2021") }
    var time by remember { mutableStateOf("6:00 PM-9:00 PM") }
    var description by remember {
        mutableStateOf(
            "Press \"L\" if you like it. I am available for new project. Send me your “Hello” here,\n" +
                    "rasel06103@gmail.com\n" +
                    "\n" +
                    "Take Care & Love from Md.Al Amin\n" +
                    "************\n" +
                    "Behance | Dribbble | Instagram | Upwork\n" +
                    "Thanks for watching."
        )
    }

    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = LightBlue.toArgb()

    var priority by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightBlue, RoundedCornerShape(bottomStart = 40.dp)),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 5.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "")

                Icon(
                    imageVector = Icons.Outlined.EditNote,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        isEdit = !isEdit
                    }
                        .size(40.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Complete your task",
                modifier = Modifier.padding(start = 20.dp),
                color = Color.Gray
            )

            BasicTextField(
                modifier = Modifier
                    .padding(start = 20.dp, top = 5.dp)
                    .fillMaxWidth(0.7f),
                value = title,
                onValueChange = {
                    title = it
                },
                readOnly = !isEdit,
                textStyle = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 2
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Jan 2024",
                    color = unselectedDate
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "",
                    tint = unselectedDate
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        Text(text = "Date", color = Color.Gray)
                        BasicTextField(
                            value = date,
                            onValueChange = {
                                date = it
                            },
                            readOnly = !isEdit,
                            textStyle = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Divider(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(2.dp)
                            .fillMaxHeight(0.1f)
                    )


                    Column(
                        modifier = Modifier.padding(top = 20.dp, end = 20.dp)
                    ) {
                        Text(text = "Time", color = Color.Gray)
                        BasicTextField(
                            value = time,
                            onValueChange = {
                                time = it
                            },
                            readOnly = !isEdit,
                            textStyle = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                }


//            Box(
//                modifier = Modifier
//                    .width(20.dp)
//                    .fillMaxHeight(0.1f)
//                    .background(LightBlue, RoundedCornerShape( bottomStart = 100.dp))
//            )

                Divider(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .height(2.dp)
                        .fillMaxWidth()
                )
            }



            Column() {
                Text(
                    text = "Description",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                BasicTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    readOnly = !isEdit,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                        .fillMaxHeight(0.6f)
                        .background(Color.LightGray.copy(0.1f), RoundedCornerShape(5.dp)),
                    textStyle = TextStyle(
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                )
            }

            Row(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                val priorityText = when (priority) {
                    0 -> "High"
                    1 -> "Medium"
                    else -> "Low"
                }

                val priorityColor = when (priority) {
                    0 -> Color.Red
                    1 -> Orange
                    else -> priority2
                }

                val backgroundColor = when (priority) {
                    0 -> Color.Red.copy(0.2f)
                    1 -> Color.Yellow.copy(0.2f)
                    else -> Color.Green.copy(0.2f)
                }

                Text(
                    text = "Priority:",
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.width(25.dp))
                Box(
                    modifier = Modifier
                        .background(color = backgroundColor, shape = CircleShape)
                ) {
                    Text(
                        text = priorityText,
                        color = priorityColor,
                        modifier = Modifier.padding(
                            start = 15.dp,
                            end = 15.dp,
                            top = 10.dp,
                            bottom = 10.dp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            if (isEdit) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Cancel",
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.clickable {
                            isEdit = !isEdit
                        }
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    Button(onClick = {

                    },
                        colors = ButtonDefaults.buttonColors(Color.Blue)
                    ) {
                        Text(text = "Submit", modifier = Modifier.padding(10.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    TaskDetailScreen(tasks(
        1,
        "Read email and reply to everyone",
        "Check all the email of inbox and reply to necessary one",
        "6:00PM",
        "7:00PM",
        "31"
    ))
}