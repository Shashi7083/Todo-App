package com.example.todoapp.Screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.Model.tasks
import com.example.todoapp.R
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.KalendarType
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.SelectableWeekCalendar
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.StaticWeekCalendar
import io.github.boguszpawlowski.composecalendar.day.DefaultDay
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


@Composable
fun Calendar(
    modifier: Modifier,
    navController: NavHostController,
    taskViewModel: TaskViewModel
) {

    val context = LocalContext.current

    var initialMonth by remember { mutableStateOf(YearMonth.now()) }
    var taskList = taskViewModel.tasks.collectAsState(emptyList())

    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

//    val datesList: List<LocalDate> = taskList.value.map { task ->
//        //LocalDate.parse("${task.date}-${task.month}-${task.year}", dateFormatter)
//    }

//    Log.d("Log",datesList.toString())




    val calendarState = rememberSelectableCalendarState(
        initialMonth = initialMonth,
        //initialSelection = datesList,
        initialSelectionMode = SelectionMode.Multiple,
        confirmSelectionChange = { false }
    )

    var Year by remember { mutableStateOf(calendarState.monthState.currentMonth.year) }
    var Month by remember { mutableStateOf(calendarState.monthState.currentMonth.month) }




    var previousMonth by remember { mutableStateOf(calendarState.monthState.currentMonth) } // Store initial month

    LaunchedEffect(calendarState) {
        snapshotFlow { calendarState.monthState.currentMonth }
            .collect { newMonthYear ->
                val newMonth = newMonthYear.month
                Month = newMonth
                if (newMonth != previousMonth.month) {
                    val newYear = newMonthYear.year
                    Year = newYear
                    Toast.makeText(context, "${newMonth}  ${newYear}", Toast.LENGTH_LONG).show()
                }
            }
    }

    var monthNo = getMonthNumber(Month.toString())

    // if you remove this monthNo not equals to tasks.month  (i don't know)
    Log.d("Log","${monthNo}")

    val filteredTaskList = taskList.value.filter { tasks ->
         monthNo.equals(tasks.month)&& tasks.year.equals(Year.toString())
    }



    Column(
        modifier = modifier
    ) {
        SelectableCalendar(
            calendarState = calendarState
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(){
            items(filteredTaskList){task ->

                calendarTask(task = task)

            }
        }


    }



}

@Composable
fun calendarTask(task : tasks) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

        ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "${task.date}",
                fontSize = 18.sp
            )

            Text(
                text = "${task.day}",
                fontSize = 15.sp
            )


        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
        ){

            Image(
                painter = painterResource(id = R.drawable.december ),
                contentDescription = null,
                contentScale= ContentScale.FillBounds,
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0.7f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ){



                Column(
                    modifier = Modifier.weight(0.7f)
                ){
                    Text(
                        text = "${task.title}",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${task.body}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.3f)
                ){
                    Text(
                        text = "${task.startTime}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "to",
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                        fontSize = 10.sp
                    )

                    Text(
                        text = "${task.endTime}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    )

                }

            }


        }



    }
}


fun getMonthNumber(monthName: String): String {
    return when (monthName.toUpperCase()) {
        "JANUARY" -> "1"
        "FEBRUARY" -> "2"
        "MARCH" -> "3"
        "APRIL" -> "4"
        "MAY" -> "5"
        "JUNE" -> "6"
        "JULY" -> "7"
        "AUGUST" -> "8"
        "SEPTEMBER" -> "9"
        "OCTOBER" -> "10"
        "NOVEMBER" -> "11"
        "DECEMBER" -> "12"
        else -> ""
    }
}





