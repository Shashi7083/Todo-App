package com.example.todoapp.Screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.Routes.settingScreenRoute.SettingScreens
import com.example.todoapp.ui.theme.TextBody
import com.example.todoapp.ui.theme.TextHeading
import com.example.todoapp.ui.theme.TextSubHeading

@Composable
fun HelpScreen(
    navController : NavController
) {

    val interactionSource = remember { MutableInteractionSource() }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .fillMaxWidth()
            .padding(15.dp)
    ) {

        Row {

           Icon(
               imageVector = Icons.Default.ArrowBack ,
               contentDescription = null,
               modifier = Modifier
                   .clickable(
                       interactionSource = interactionSource,
                       indication = null
                   ) {
                       navController.navigate(SettingScreens.SettingScreen.route)
                   }
           )
            Text(
                text = "Help & About us",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextHeading
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.about_app), fontSize = 13.sp, color = TextBody)
        Spacer(modifier = Modifier.height(15.dp))
        Componenet1()
        Component2()
        Componenet1()
        Component2()
        Componenet1()
        Component2()
    }
}


@Composable
fun Componenet1(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .background(Color.Green, shape = RoundedCornerShape(10.dp))
                .height(150.dp)
                .weight(0.3f)
        ) {

        }
        Spacer(modifier = Modifier.width(8.dp))

        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(0.7f)
        ){
            Text(
                text = "Create your task",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextSubHeading
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.about_app), fontSize = 13.sp, color = TextBody)

        }

    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun Component2(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {




        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(0.7f)
        ){
            Text(
                text = "Task list and Delete task",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextSubHeading
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.about_app), fontSize = 13.sp, color = TextBody)

        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .background(Color.Green, shape = RoundedCornerShape(10.dp))
                .height(150.dp)
                .weight(0.3f)
        ) {

        }

    }

    Spacer(modifier = Modifier.height(15.dp))
}