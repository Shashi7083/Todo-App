package com.example.todoapp.Screens



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.himanshoe.kalendar.endlos.DaySelectionMode
import com.himanshoe.kalendar.endlos.Kalendar
import com.himanshoe.kalendar.endlos.KalendarEarthy
import com.himanshoe.kalendar.endlos.model.KalendarEvents
import com.himanshoe.kalendar.endlos.paging.rememberKalendarPagingController
import com.himanshoe.kalendar.endlos.ui.color.KalendarColor
import com.himanshoe.kalendar.endlos.ui.color.KalendarColors
import com.himanshoe.kalendar.endlos.ui.day.KalendarDayKonfig
import com.himanshoe.kalendar.endlos.ui.header.KalendarTextKonfig
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import com.himanshoe.kalendar.endlos.KalendarEarthy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.himanshoe.kalendar.endlos.KalendarType
import com.himanshoe.kalendar.endlos.model.KalendarEvent
import java.time.Clock
import java.util.TimeZone


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calendar(
    modifier : Modifier,
    navController : NavHostController,
    taskViewModel: TaskViewModel
) {

//    var syste = Clock.system
//
//    Column(
//        modifier = modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//
//        val eventDate = LocalDate.of(2023, 10, 11)
//        val events = (0..5).map {
//
//
//            KalendarEvent(
//                            date =  Clock.syste,
//                            eventName = "Test event",
//                            eventDescription = null
//                        )
//                    }
//                    val events1 = (0..5).map {
//                        com.himanshoe.kalendar.KalendarEvent(
//                            date = Clock.System.todayIn(
//                                TimeZone.currentSystemDefault()
//                            ).plus(it, DateTimeUnit.DAY),
//                            eventName = it.toString(),
//                        )
//                    }
//                    com.himanshoe.kalendar.Kalendar(
//                        currentDay = Clock.System.todayIn(
//                            TimeZone.currentSystemDefault()
//                        ),
//                        kalendarType = KalendarType.Horizontal,
//                        events = com.himanshoe.kalendar.KalendarEvents(events1 + events1 + events1)
//
//                    )
//                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
//
//                    com.himanshoe.kalendar.Kalendar(
//                        currentDay = Clock.System.todayIn(
//                            TimeZone.currentSystemDefault()
//                        ),
//                        kalendarType = KalendarType.Horizontal,
//                        daySelectionMode = DaySelectionMode.Range,
//                        events = com.himanshoe.kalendar.KalendarEvents(events1 + events1 + events1),
//                        onRangeSelected = { range, rangeEvents ->
//                            Log.d(":SDfsdfsdfdsfsdfsdf",range.toString())
//                            Log.d(":SDfsdfsdfdsfsdfsdf",rangeEvents.toString())
//                        }
//                    )
//                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
//
//                    Kalendar(
//                        modifier = Modifier.fillMaxSize(),
//                        events = KalendarEvents(events + events)
//                    )
//
//
//    }

}






