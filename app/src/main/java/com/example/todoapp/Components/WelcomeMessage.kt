package com.example.todoapp.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.todoapp.ui.theme.LightGray
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.Model.taskList
import java.util.Calendar

@Composable
@Preview(showBackground = true)
fun WelcomeMessage() {

    var day by remember { mutableStateOf("") }
    val calendar = Calendar.getInstance()
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    var listSize by remember {
        mutableStateOf(taskList.size)
    }

    when (dayOfWeek) {
        1 -> day = "Sunday"
        2 -> day = "Monday"
        3 -> day = "Tuesday"
        4 -> day = "Wednesday"
        5 -> day = "Thrusday"
        6 -> day = "Friday"
        7 -> day = "Saturday"
        else -> day = "Unknown day of week"
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        Text(
            text = "Hi Shashi! 👋",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Text(
            text = "$listSize tasks for today $day",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = LightGray
        )
    }
}