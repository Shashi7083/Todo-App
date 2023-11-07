package com.example.todoapp.Screens

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.MoreTime
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.MainActivity
import com.example.todoapp.Model.CalendarDateModel
import com.example.todoapp.Model.tasks
import com.example.todoapp.RoomDatabase.TaskViewModel
import com.example.todoapp.ui.theme.LightBlue
import com.example.todoapp.ui.theme.Orange
import com.example.todoapp.ui.theme.gray
import com.example.todoapp.ui.theme.redOrange

import com.example.todoapp.ui.theme.unselectedDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun CreateTask(
    taskViewModel: TaskViewModel
) {

    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    var sdf by remember { mutableStateOf(SimpleDateFormat("MMMM YYYY", Locale.ENGLISH)) }
    var cal by remember { mutableStateOf(Calendar.getInstance(Locale.ENGLISH)) }
    var currentDate by remember { mutableStateOf(Calendar.getInstance(Locale.ENGLISH)) }
    val dates by remember { mutableStateOf(ArrayList<Date>()) }
    //  val calendarList2 = ArrayList<CalendarDateModel>()
    var calendarList by remember { mutableStateOf(ArrayList<CalendarDateModel>()) }


    var nextMonth by remember { mutableStateOf("") }
    var prevMonth by remember { mutableStateOf("") }
    var currDateMonth by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(1) }


    //Set Up Calendar

    // Get the previous and next months
    val previousMonth = cal.clone() as Calendar
    previousMonth.add(Calendar.MONTH, -1)

    val nxtMonth = cal.clone() as Calendar
    nxtMonth.add(Calendar.MONTH, 1)

    // Format the previous and next months
    val previousMonthString = sdf.format(previousMonth.time)
    val nextMonthString = sdf.format(nxtMonth.time)

    prevMonth = previousMonthString.substring(0, 3)
    nextMonth = nextMonthString.substring(0, 3)

    val monthFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    val yearFormat = SimpleDateFormat("YYYY", Locale.ENGLISH)




    calendarList = ArrayList<CalendarDateModel>()
    currDateMonth = sdf.format(cal.time)
    val monthCalendar = cal.clone() as Calendar
    val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
    dates.clear()
    monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

    while (dates.size < maxDaysInMonth) {
        dates.add(monthCalendar.time)
        calendarList.add(CalendarDateModel(monthCalendar.time))
        monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
    }
//    Log.d("test"," "+calendarList.get(0) +"\n "+)
    //End Set Up Calendar

    var selectedItem by remember {
        mutableStateOf(
            CalendarDateModel(Calendar.getInstance(Locale.ENGLISH).time)
        )
    }


    var month = ""
    var year = ""
    month = selectedItem.data.month.toString()
    year = yearFormat.format(cal.time)


    val isSystem24Hour = android.text.format.DateFormat.is24HourFormat(context)

    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]


    val startTimePicker = TimePickerDialog(
        context,
        {_,h:Int, m:Int->

            var hour = h
            var minute = m

            var amPm = ""
            if(!isSystem24Hour){
                if(hour <12){
                   amPm= " AM"

                }
                else{
                   amPm= " PM"
                    hour -= 12
                }
            }else{
                if(hour < 12){
                    amPm = " AM"
                }else{
                    amPm = " PM"
                }
            }
            var hourStr :String =if(hour/10 <=0)"0$hour" else "$hour"
            var minuteStr :String =if(minute/10 <=0)"0$minute" else "$minute"
            startTime = "$hourStr:$minuteStr $amPm"

        },hour,minute,isSystem24Hour
    )
    val endTimePicker = TimePickerDialog(
        context,
        { _, h: Int, m: Int ->

            var hour = h
            var minute = m

            var amPm = ""
            if(!isSystem24Hour){
                if(hour <12){
                    amPm= " AM"

                }
                else{
                    amPm= " PM"
                    hour -= 12
                }
            }else{
                if(hour < 12){
                    amPm = " AM"
                }else{
                    amPm = " PM"
                }
            }
            var hourStr :String =if(hour/10 <=0)"0$hour" else "$hour"
            var minuteStr :String =if(minute/10 <=0)"0$minute" else "$minute"
            endTime = "$hourStr:$minuteStr $amPm"


        },hour,minute,isSystem24Hour
    )

    Log.d("test", monthCalendar.time.toString())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .background(LightBlue)
//                .weight(0.3f)Color(0xFFFFFBF2)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(LightBlue)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                )



                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Create New Task",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 0.dp, 15.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                cal.add(Calendar.MONTH, -1)
                                month = selectedItem.data.month.toString()
                                year = yearFormat.format(cal.time)
                                // Get the previous and next months
                                val previousMonth = cal.clone() as Calendar
                                previousMonth.add(Calendar.MONTH, -1)

                                val nxtMonth = cal.clone() as Calendar
                                nxtMonth.add(Calendar.MONTH, 1)

                                // Format the previous and next months
                                val previousMonthString = sdf.format(previousMonth.time)
                                val nextMonthString = sdf.format(nxtMonth.time)

                                prevMonth = previousMonthString.substring(0, 3)
                                nextMonth = nextMonthString.substring(0, 3)

                                calendarList = ArrayList<CalendarDateModel>()
                                currDateMonth = sdf.format(cal.time)
                                val monthCalendar = cal.clone() as Calendar
                                val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
                                dates.clear()
                                monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
                                while (dates.size < maxDaysInMonth) {
                                    dates.add(monthCalendar.time)
                                    calendarList.add(CalendarDateModel(monthCalendar.time))
                                    monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
                                }


                            }
                    ) {

                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            text = prevMonth,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }


                    Text(
                        text = currDateMonth,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )




                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                cal.add(Calendar.MONTH, 1)

                                month = selectedItem.data.month.toString()
                                year = yearFormat.format(cal.time)
                                // Get the previous and next months
                                val previousMonth = cal.clone() as Calendar
                                previousMonth.add(Calendar.MONTH, -1)

                                val nxtMonth = cal.clone() as Calendar
                                nxtMonth.add(Calendar.MONTH, 1)

                                // Format the previous and next months
                                val previousMonthString = sdf.format(previousMonth.time)
                                val nextMonthString = sdf.format(nxtMonth.time)

                                prevMonth = previousMonthString.substring(0, 3)
                                nextMonth = nextMonthString.substring(0, 3)


                                calendarList = ArrayList<CalendarDateModel>()
                                currDateMonth = sdf.format(cal.time)
                                val monthCalendar = cal.clone() as Calendar
                                val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
                                dates.clear()
                                monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
                                while (dates.size < maxDaysInMonth) {
                                    dates.add(monthCalendar.time)
                                    calendarList.add(CalendarDateModel(monthCalendar.time))
                                    monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
                                }

                            }
                    ) {


                        Text(
                            text = nextMonth,
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                CalendarListView(
                    calendarList,
                    selectedItem
                ) { selectedDate ->

                    selectedItem = selectedDate
                }

            }

        }
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
//                .weight(0.7f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),

                ) {
                Text(
                    text = "Schedule",
                    modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text(
                            text = "Title",
                            color = Color.LightGray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp), shape = RoundedCornerShape(40.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = body,
                    onValueChange = {
                        body = it
                    },
                    label = {
                        Text(
                            text = "Description",
                            color = Color.LightGray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    shape = RoundedCornerShape(40.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp, 10.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = startTime,
                        onValueChange = {
                            startTime = it
                        },
                        enabled = false,
                        placeholder = {
                            Text(text = "00:00 AM")
                        },
                        label = {
                            Text(
                                text = "Start time",
                                color = Color.LightGray
                            )
                        },
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 5.dp, 0.dp)
                            .weight(0.5f)
                            .clickable {
                                startTimePicker.show()
                            },
                        shape = RoundedCornerShape(40.dp),
                        trailingIcon = {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(redOrange.copy(0.4f))
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.MoreTime,
                                    contentDescription = "timer",
                                    tint = redOrange
                                )
                            }
                        }
                    )


                    OutlinedTextField(
                        value = endTime,
                        onValueChange = {
                            endTime = it
                        },
                        enabled = false,
                        placeholder = {
                            Text(text = "00:00 AM")
                        },
                        label = {
                            Text(
                                text = "End time",
                                color = Color.LightGray)
                        },
                        modifier = Modifier
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            .weight(0.5f)
                            .clickable {
                                endTimePicker.show()
                            },
                        shape = RoundedCornerShape(40.dp),
                        trailingIcon = {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(redOrange.copy(0.4f))
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.MoreTime,
                                    contentDescription = "timer",
                                    tint = redOrange
                                )
                            }
                        },
                        maxLines = 1
                    )


                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Priority",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 10.dp, 0.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Red.copy(0.1f))
                            .clickable {
                                priority = 0
                            }
                            .border(
                                shape = CircleShape,
                                width = if (priority == 0) 2.dp else 0.dp,
                                color = Color.Red
                            )

                    ) {

                        Text(
                            text = "High",
                            fontSize = 18.sp,
                            color = Color.Red,
                            modifier = Modifier.padding(25.dp, 15.dp, 25.dp, 15.dp)
                        )


                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Orange.copy(0.1f))
                            .clickable {
                                priority = 1
                            }
                            .border(
                                shape = CircleShape,
                                width = if (priority == 1) 2.dp else 0.dp,
                                color = Orange
                            )

                    ) {

                        Text(
                            text = "Midium",
                            fontSize = 18.sp,
                            color = Orange,
                            modifier = Modifier.padding(25.dp, 15.dp, 25.dp, 15.dp)
                        )

                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Green.copy(0.1f))
                            .clickable {
                                priority = 2
                            }
                            .border(
                                shape = CircleShape,
                                width = if (priority == 2) 2.dp else 0.dp,
                                color = Color.Green
                            )

                    ) {

                        Text(
                            text = "Low",
                            fontSize = 18.sp,
                            color = Color.Green,
                            modifier = Modifier.padding(25.dp, 15.dp, 25.dp, 15.dp)
                        )


                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 0.dp, 0.dp, 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        elevation = ButtonDefaults.buttonElevation(2.dp),
                        onClick = {
                            val task = tasks(
                                title = title,
                                body = body,
                                startTime = startTime,
                                endTime = endTime,
                                date = selectedItem.calendarDate,
                                priority = priority,
                                day = selectedItem.calendarDay,
                                month = month,
                                year = year
                            )


                            if(!title.equals("") && !startTime.equals("") && !endTime.equals("")) {
                                taskViewModel.insertTask(task)
                                title = ""
                                body = ""
                                startTime=""
                                endTime=""
                                priority = 1
                                Toast.makeText(context, "Task Added Successfully", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Field Cannot be Empty", Toast.LENGTH_SHORT).show()
                            }

                        },
                        colors = ButtonDefaults.buttonColors()
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        )
                        {
                            Text(
                                "Create  Task",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(10.dp)
                            )

                            Icon(
                                imageVector = Icons.Filled.ArrowForwardIos,
                                contentDescription = "arrow",
                                modifier = Modifier.size(10.dp)
                            )
                        }
                    }
                }


            }
        }
    }
}


@Composable
fun CalendarListView(
    calendarList: ArrayList<CalendarDateModel>,
    selectedItem: CalendarDateModel,
    selectedItemDetails: (CalendarDateModel) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 5.dp)
    ) {
        items(calendarList) {
            dateList(
                date = it,
                selectedItem = selectedItem,
                selectedItemDetails = selectedItemDetails
            )

        }
    }
}

@Composable
fun dateList(
    date: CalendarDateModel,
    selectedBackgroundColor: Color = unselectedDate,
    unselectedBackgroundColor: Color = Color.White,
    selectedDateColor: Color = Color.White,
    selectedDayColor: Color = Color.LightGray,
    unselectedDateColor: Color = unselectedDate,
    unselectedDayColor: Color = gray,
    selectedItem: CalendarDateModel,
    selectedItemDetails: (CalendarDateModel) -> Unit
) {

    var dateColor = unselectedDateColor
    var dayColor = unselectedDayColor
    var backgroundColor = unselectedBackgroundColor



    if ((selectedItem.data.date == date.data.date) && (selectedItem.data.month == date.data.month) && (selectedItem.data.year == date.data.year)) {
        dateColor = selectedDateColor
        dayColor = selectedDayColor
        backgroundColor = selectedBackgroundColor
    } else {
        dateColor = unselectedDateColor
        dayColor = unselectedDayColor
        backgroundColor = unselectedBackgroundColor
    }

    val roundedCornerShape = RoundedCornerShape(size = 38.dp)
    Card(
        modifier = Modifier
            .width(70.dp)
            .padding(8.dp, 10.dp, 8.dp, 10.dp)
            .clickable {
                selectedItemDetails(date)
            },
        // .background(Color.Cyan, roundedCornerShape),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(backgroundColor),
        shape = roundedCornerShape
    ) {
        Column(
            modifier = Modifier.padding(10.dp, 15.dp, 10.dp, 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.calendarDate,
                fontSize = 25.sp,
                color = dateColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = date.calendarDay,
                color = dayColor,
                fontSize = 15.sp
            )

        }
    }
}


