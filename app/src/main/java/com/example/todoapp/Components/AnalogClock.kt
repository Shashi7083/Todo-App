package com.example.todoapp.Components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.gray
import com.example.todoapp.ui.theme.redOrange
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Date
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun OnScreen(modifier : Modifier){
    var currentTimeInMs by remember {
        mutableStateOf(System.currentTimeMillis())
    }

    LaunchedEffect(key1 = true){
        while(true){
            delay(200)
            currentTimeInMs = System.currentTimeMillis()
        }
    }

    Box(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(.5f)
        ,
        contentAlignment = Alignment.Center
    ){
        Clock(
            modifier = Modifier
                .size(500.dp),
            time = {
                currentTimeInMs
            },
            circleRadius = 600f,
            outerCircleThickness = 50f
        )
    }

}

@Composable
fun Clock(
    modifier: Modifier = Modifier,
    time:()->Long,
    circleRadius:Float,
    outerCircleThickness:Float,
) {

    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }
    Box(
        modifier = modifier
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            val width = size.width
            val height = size.height
            circleCenter = Offset(x = width/2f, y = height/2f)
            val date = Date(time())
            val cal = Calendar.getInstance()
            cal.time = date
            val hours = cal.get(Calendar.HOUR_OF_DAY)
            val minutes = cal.get(Calendar.MINUTE)
            val seconds = cal.get(Calendar.SECOND)



            drawCircle(
                style = Stroke(
                    width = outerCircleThickness
                ),
                brush = Brush.linearGradient(
                    listOf(
                        Color.White.copy(0.45f),
                        Color.DarkGray.copy(0.35f)
                    )
                ),
                radius = circleRadius+outerCircleThickness/2f,
                center = circleCenter
            )
            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        Color.White.copy(0.45f),
                        Color.White.copy(0.25f)
                    )
                ),
                radius = circleRadius,
                center = circleCenter
            )
            drawCircle(
                color = gray,
                radius = 15f,
                center = circleCenter
            )

            val littleLineLength = circleRadius*0.1f
            val largeLineLength = circleRadius*0.2f
            for(i in 0 until 60){
                val angleInDegrees = i*360f/60
                val angleInRad = angleInDegrees * PI / 180f + PI /2f
                val lineLength = if(i%5 == 0)largeLineLength else littleLineLength
                val lineThickness = if(i%5 == 0) 5f else 2f

                val start = Offset(
                    x = (circleRadius * cos(angleInRad) + circleCenter.x).toFloat(),
                    y = (circleRadius * sin(angleInRad) + circleCenter.y).toFloat()
                )

                val end = Offset(
                    x = (circleRadius * cos(angleInRad) + circleCenter.x).toFloat(),
                    y = (circleRadius * sin(angleInRad) + lineLength + circleCenter.y).toFloat()
                )
                rotate(
                    angleInDegrees+180,
                    pivot = start
                ){
                    drawLine(
                        color = gray,
                        start = start,
                        end = end,
                        strokeWidth = lineThickness.dp.toPx()
                    )
                }
            }

            val clockHands = listOf(ClockHands.Seconds,ClockHands.Minutes,ClockHands.Hours)

            clockHands.forEach { clockHand ->
                val angleInDegrees = when (clockHand) {
                    ClockHands.Seconds -> {
                        seconds * 360f/60f
                    }
                    ClockHands.Minutes -> {
                        (minutes + seconds/60f) * 360f/60f
                    }
                    ClockHands.Hours -> {
                        (((hours%12)/12f*60f)+minutes/12f) * 360f/60f
                    }
                }

                val lineLength = when(clockHand){
                    ClockHands.Seconds -> {
                        circleRadius * 0.8f
                    }
                    ClockHands.Minutes -> {
                        circleRadius * 0.7f
                    }
                    ClockHands.Hours -> {
                        circleRadius * 0.5f
                    }
                }
                val lineThickness = when(clockHand){
                    ClockHands.Seconds -> {
                        3f
                    }
                    ClockHands.Minutes -> {
                        7f
                    }
                    ClockHands.Hours -> {
                        9f
                    }
                }
                val start = Offset(
                    x = circleCenter.x,
                    y = circleCenter.y
                )

                val end = Offset(
                    x = circleCenter.x,
                    y = lineLength + circleCenter.y
                )
                rotate(
                    angleInDegrees-180,
                    pivot = start
                ){
                    drawLine(
                        color = if(clockHand == ClockHands.Seconds) redOrange else gray,
                        start = start,
                        end = end,
                        strokeWidth = lineThickness.dp.toPx()
                    )
                }
            }
        }
    }
}

enum class ClockHands {
    Seconds,
    Minutes,
    Hours
}
