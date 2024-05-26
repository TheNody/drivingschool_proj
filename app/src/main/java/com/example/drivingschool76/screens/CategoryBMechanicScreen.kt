package com.example.drivingschool76.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.drivingschool76.components.ExpandableBoxForCatB
import com.example.drivingschool76.utils.SHOPPINGCART_SCREEN

@Composable
fun CategoryBMechanicScreen(navController: NavController) {

    val listState = rememberLazyListState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    val imageUrl = "https://sun9-32.userapi.com/impg/jJl0lIgM3koNqG62-iuHqzRTzwU6azycqQlLmg/-McKrzwV-T0.jpg?size=533x530&quality=95&sign=cc89a0b553e8d5dcb53dc9afbdef4691&c_uniq_tag=xR7JB77zyGu3GOEpyWKo7HgZROg3zskJlysj9AOy-_o&type=album"
                    val painter = rememberAsyncImagePainter(model = imageUrl)

                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .height(250.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ExpandableBoxForCatB()

                    Spacer(modifier = Modifier.height(70.dp))
                }
            }

            Spacer(modifier = Modifier.height(70.dp))


            FloatingActionButton(
                onClick = { navController.navigate(SHOPPINGCART_SCREEN) },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(6.dp)
                    .size(40.dp),
                shape = RoundedCornerShape(50),
                containerColor = Color.LightGray
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to shopping cart",
                    tint = Color.Black
                )
            }

            val showFloatingButton by remember {
                derivedStateOf {
                    val layoutInfo = listState.layoutInfo
                    val visibleItemsInfo = layoutInfo.visibleItemsInfo
                    if (visibleItemsInfo.isNotEmpty()) {
                        val lastVisibleItem = visibleItemsInfo.last()
                        lastVisibleItem.index == layoutInfo.totalItemsCount - 1 &&
                                lastVisibleItem.offset + lastVisibleItem.size <= layoutInfo.viewportEndOffset
                    } else {
                        false
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = if (showFloatingButton) 0.dp else 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {

                    Spacer(modifier = Modifier.height(70.dp))

                    Button(
                        onClick = { /* TODO: Handle payment logic */ },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                    ) {
                        Text(text = "Оплатить подписку")
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}