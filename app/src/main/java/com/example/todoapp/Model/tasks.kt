package com.example.todoapp.Model

data class tasks(
    val id : Int,
    val title : String,
    val body : String?= null,
    val startTime : String,
    val endTime : String,
    val date : String ="",
    val priority : Int = 1,
    val day : String = "",
    val month : String ="",
    val year : String =""
)

val taskList = listOf(
    tasks(
        1,
        "Sleep",
        "Try to go to bed early ",
        "03:00",
        "12:30",
        priority = 1
    ),
    tasks(
        2,
        "College",
        "Go to College and make all class attendence",
        "12:35",
        "01:00",
        priority = 0
    ),
    tasks(
        3,
        "Lunch",
        "Try to eat lunch, clean your plate",
        "01:20",
        "02:20",
    ),
    tasks(
        4,
        "Clean Room",
        "Tum se na ho payega , chhor do",
        "02:25",
        "02:26"
    ),
    tasks(
        5,
        "Break",
        "Scroll reels , shorts, facebook , snapchat ",
        "02:27",
        "06:25",
    ),
    tasks(
        6,
        "Buy Vegetables",
        "Go to market and buy some fresh vegetables",
        "06:27",
        "07:30"
    ),
    tasks(
        7,
        "Reels,Snapchat",
        "Watch reels and send snaps",
        "07:31",
        "11:30"
    ),
    tasks(
        8,
        "Dinner",
        "Eat dinner ,don't waste food",
        "12:00",
        "01:00"
    ),
    tasks(
        9,
        "Study",
        "Study (galti se)",
        "01:30",
        "02:30",
    )

)
