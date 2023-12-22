package com.example.todoapp.Routes.settingScreenRoute

sealed class SettingScreens (
    val route: String,
    val title :String
){
    object HelpScreen : SettingScreens(
        title ="Help",
        route = "setting_help_screen"
    )

    object SettingScreen : SettingScreens(
        title = "Setting",
        route = "setting_screen"
    )
}