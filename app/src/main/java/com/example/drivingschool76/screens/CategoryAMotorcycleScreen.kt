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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.drivingschool76.components.ExpandableBox
import com.example.drivingschool76.utils.SHOPPINGCART_SCREEN

@Composable
fun CategoryAMotorcycleScreen(navController: NavController) {

    val listState = rememberLazyListState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    val imageUrl = "https://sun9-31.userapi.com/impg/Wbs1xEG3HRGpE5cLzvcYoew6wdIMOmGh6zLx0w/l1TvUKhEj_0.jpg?size=1066x1060&quality=95&sign=282d69c86b50365c1324036ea36d8a6d&type=album"
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

                    Text(
                        text = "Обучение категории \"А\" проводится на мотоциклах:",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = "Лёгкий Стелс",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )

                    ExpandableBox(
                        shortText = "Преимущества Лёгкий Стелс перед другими мотоциклами делают его идеальным выбором для обучения и сдачи экзамена на права категории \"A\"",
                        fullText = "Преимущества Лёгкий Стелс перед другими мотоциклами делают его идеальным выбором для обучения и сдачи экзамена на права категории \"A\". Во-первых, благодаря своему легкому весу и компактным габаритам, он гораздо проще в управлении, особенно для новичков. Во-вторых, его маневренность и стабильность на дороге позволяют уверенно проходить любые упражнения, требуемые на экзамене. Наконец, Лёгкий Стелс оборудован современными системами безопасности, что повышает уровень комфорта и уверенности водителя во время езды."
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Рейсер 250",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    ExpandableBox(
                        shortText = "Рейсер 250 выделяется среди других мотоциклов благодаря своему оптимальному соотношению мощности и управляемости, что делает его идеальным выбором для новичков.",
                        fullText = "Рейсер 250 выделяется среди других мотоциклов благодаря своему оптимальному соотношению мощности и управляемости, что делает его идеальным выбором для новичков. Легкий вес и компактные размеры Рейсер 250 обеспечивают отличную маневренность и стабильность на дороге, что особенно важно при сдаче экзамена на права категории \"A\". Дополнительно, этот мотоцикл оснащен современными системами безопасности и комфорта, которые способствуют уверенности и спокойствию водителя. Простота в управлении и надежность Рейсер 250 позволяют успешно проходить все необходимые упражнения на экзамене."
                    )

                    Spacer(modifier = Modifier.height(70.dp))
                }
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
        }
    }
}