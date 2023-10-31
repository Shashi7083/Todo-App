package com.example.todoapp.Screens


const val BOTTOM_NAVIGATION_ROUTE = "bottom_navigation_route"
const  val OTHER_ROUTE = "other_route"
const val ROOT_ROUTE = "root_route"

sealed class BottomBarScreens(
    val route: String,
    val title: String
) {
    object Home : BottomBarScreens(
        title = "Home",
        route = "bottom_home_screen"
    )

    object Calendar : BottomBarScreens(
        route = "calendar_screen",
        title = "bottom_Calendar"
    )

    object Notification : BottomBarScreens(
        route = "bottom_notification_screen",
        title = "Notification"
    )

    object AddTask : BottomBarScreens(
        route = "add_task_screen",
        title = "Add Task"
    )

    object CreateTask : BottomBarScreens(
        route = "create_task_screen",
        title = "Create Task"
    )
}