package com.example.todoapp.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.todoapp.RoomDatabase.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.Components.WelcomeMessage
import com.example.todoapp.QuickListDatabase.TodoEntity
import com.example.todoapp.QuickListDatabase.addDate
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import com.example.todoapp.R
import com.example.todoapp.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickListTaskScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: QuickTaskViewModel = viewModel()
) {

    val todos by viewModel.todos.collectAsState()

    val (dialogOpen, setDialogOpen) = remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {

        val (title, setTitle) = remember {
            mutableStateOf("")
        }

        val (subTitle, setSubtitle) = remember {
            mutableStateOf("")
        }

        Dialog(onDismissRequest = { setDialogOpen(false) }) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            ){
                Image(
                    painter = painterResource(id = R.drawable.input ),
                    contentDescription = null,
                    contentScale= ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Quick Todo",
                    modifier = Modifier.padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        setTitle(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Title")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedLabelColor = Color(0xFF131313),
                        unfocusedBorderColor = Color(0xFF131313)
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = subTitle,
                    onValueChange = {
                        setSubtitle(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Sub Title")
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedLabelColor = Color(0xFF131313),
                        unfocusedBorderColor = Color(0xFF131313)
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        if (title.isNotEmpty()) {
                            viewModel.addTodo(
                                TodoEntity(
                                    title = title,
                                    subTitle = subTitle
                                )
                            )

                            setDialogOpen(false)
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Text(text = "Submit", color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            }
        }
        }
    }

//    Column(
//        modifier = modifier
//            .fillMaxSize()
//            .background(Color.Red),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {


    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    setDialogOpen(true)
                },
                backgroundColor = Color.White,
                contentColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            AnimatedVisibility(
                visible = todos.isEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                Text(text = "Add Quick Tasks Yet")

            }


            AnimatedVisibility(
                visible = todos.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                            start = 8.dp
                        ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    item {
                       TopMessage()
                    }

                    items(todos.sortedBy { it.done }, key = {
                        it.id
                    }) { todo ->
                        TodoItem(
                            todo = todo,
                            onClick = { viewModel.updateTodo(todo.copy(done = !todo.done)) },
                            onDelete = {
                                viewModel.deleteTodo(todo)
                            })
                    }
                }
            }

        }
    }
//    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(todo: TodoEntity, onClick: () -> Unit, onDelete: () -> Unit) {

    val color by animateColorAsState(
        targetValue = if (todo.done) Color(0xff24d65f) else Color(0xffff6363),
        animationSpec = tween(500)
    )



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement()
            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
//            .background(painter = painterResource(id =if (todo.done) R.drawable.green_background else R.drawable.red_background )),
        contentAlignment = Alignment.BottomEnd
    ) {
//
        Image(
            painter = painterResource(id = if (todo.done) R.drawable.green_background else R.drawable.red_background),
            contentDescription = null,
            contentScale= ContentScale.FillBounds,
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(8.dp))
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            //.background(color)
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 8.dp,
                vertical = 16.dp
            ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(0.8f)
            ) {

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        //  .background(MaterialTheme.colors.primary)
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Row {
                        AnimatedVisibility(
                            visible = todo.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(Icons.Default.CheckBox, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
                        }
                    }

                    Row {
                        AnimatedVisibility(
                            visible = !todo.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(Icons.Default.CheckBoxOutlineBlank, contentDescription = null, tint = Color.White, modifier = Modifier.size(40.dp))
                        }
                    }

                }

                Column {
                    Text(
                        text = todo.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )

                    Text(
                        text = todo.subTitle,
                        fontSize = 12.sp,
                        color = Color(0xFF673AB7)
                    )
                }

            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .padding(4.dp)
                    .weight(0.1f)
                  ,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Delete,

                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onDelete()
                    }

                )
            }
        }
        Text(
            modifier = Modifier.padding(4.dp),
            text = todo.addDate,
            color = Color(0xFF070707),
            fontSize = 10.sp
        )

    }

}

@Composable
fun TopMessage(){
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
            text = "Complete your Quick List tasks.",
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = LightGray
        )
    }
}