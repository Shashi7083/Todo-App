package com.example.todoapp.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.Model.taskList
import com.example.todoapp.Model.tasks
import com.example.todoapp.ui.theme.LightBlue
import com.example.todoapp.ui.theme.LightGray
import com.example.todoapp.ui.theme.LightGreen
import com.example.todoapp.ui.theme.LightPurple
import com.example.todoapp.ui.theme.Orange
import com.example.todoapp.ui.theme.priority2

@Composable
fun TaskView(
    task: tasks
) {

    val taskColor = listOf<Color>(LightPurple, LightBlue, LightGreen).random()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${task.startTime}\nAM",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(3.dp, Color.Black),
                        shape = CircleShape
                    )
            )
            Divider(
                modifier = Modifier.width(6.dp),
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(taskColor)
                        .weight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${task.title}",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(
                                top = 12.dp,
                                start = 12.dp
                            )
                    )
                    if (task.date != null && task.date != "") {
                        Text(
                            text = task.date+"-"+task.month+"-"+task.year,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .padding(
                                    start = 12.dp
                                ),
                            color = Color.Gray
                        )
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${task.startTime} - ${task.endTime}",
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    bottom = 12.dp
                                ),
                        )

                        Box(
                            modifier = Modifier.padding(end = 15.dp, bottom = 5.dp)
                        ) {
                            val priority = when (task.priority) {
                                0 -> "High"
                                1 -> "Medium"
                                else -> "Low"
                            }

                            val priorityColor = when (task.priority) {
                                0 -> Color.Red
                                1 -> Orange
                                else -> priority2
                            }

                            val backgroundColor = when (task.priority) {
                                0 -> Color.Red.copy(0.1f)
                                1 -> Color.Yellow.copy(0.1f)
                                else -> Color.Green.copy(0.1f)
                            }

                            Box(
                                modifier = Modifier
                                    .background(
                                        backgroundColor,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {

                                Text(
                                    text = "$priority",
                                    color = priorityColor,
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        top = 2.dp,
                                        end = 5.dp,
                                        bottom = 2.dp
                                    ),
                                    fontSize = 12.sp
                                )


                            }
                        }
                    }
                }

                Divider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f),
                    color = Color.Black
                )
            }


        }


    }
}

