package com.example.drivingschool76.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.AdCard
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.utils.CATEGORYB_AUTH
import com.example.drivingschool76.utils.CATEGORYB_MECH
import com.example.drivingschool76.utils.MOTORCYCLE
import kotlinx.coroutines.launch

@Composable
fun ShoppingCartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val userRoleState by loginViewModel.userStateRole.collectAsState()

    var toolbarOffsetHeightPx by remember { mutableFloatStateOf(0f) }
    var bottomBarOffsetHeightPx by remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                toolbarOffsetHeightPx = (toolbarOffsetHeightPx + available.y).coerceIn(-200f, 0f)
                bottomBarOffsetHeightPx = (bottomBarOffsetHeightPx - available.y).coerceIn(-200f, 0f)
                return Offset.Zero
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavDrawer(
                navController = navController,
                drawerState = drawerState,
                loginView = loginViewModel
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = stringResource(R.string.shopping_subscription),
                    navController = navController,
                    navigationIconClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                )
            },
            bottomBar = {
                when (userRoleState) {
                    UserRoleState.User -> CustomBottomBar(navController)
                    UserRoleState.Instructor -> InstructorBottomBar(navController)
                    UserRoleState.Manager -> ManagerBottomBar(navController)
                    UserRoleState.Authenticated -> CustomBottomBar(navController)
                    else -> {}
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection)
            ) {
                item {
                    AdCard(
                        title = "ОБУЧЕНИЕ КАТЕГОРИИ \"B\"",
                        subtitle = "АВТОМАТ",
                        price = "30 250 ₽",
                        oldPrice = "32 500 ₽",
                        description = "Легковые автомобили (автомат)",
                        imageUrl = "https://sun9-24.userapi.com/impg/2BVEml-9Sz0pM7p9qx-WjGPwUstW0pkuRR4Bbw/BFXoeJPFoTw.jpg?size=533x530&quality=95&sign=d2df4ede843d7fa24339632f3811765c&c_uniq_tag=mWCMwl0dcL2ZGQcraOxvKgWTNS3BzH52RzFR877gtnU&type=album",
                        onCardClick = { navController.navigate(CATEGORYB_AUTH) }
                    )
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    AdCard(
                        title = "ОБУЧЕНИЕ КАТЕГОРИИ \"B\"",
                        subtitle = "МЕХАНИКА",
                        price = "28 200 ₽",
                        oldPrice = "35 000 ₽",
                        description = "Легковые автомобили (механика)",
                        imageUrl = "https://sun9-32.userapi.com/impg/jJl0lIgM3koNqG62-iuHqzRTzwU6azycqQlLmg/-McKrzwV-T0.jpg?size=533x530&quality=95&sign=cc89a0b553e8d5dcb53dc9afbdef4691&c_uniq_tag=xR7JB77zyGu3GOEpyWKo7HgZROg3zskJlysj9AOy-_o&type=album",
                        onCardClick = { navController.navigate(CATEGORYB_MECH) }
                    )
                }

                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    AdCard(
                        title = "ОБУЧЕНИЕ КАТЕГОРИИ \"A\"",
                        subtitle = "",
                        price = "15 000 ₽",
                        oldPrice = "18 000 ₽",
                        description = "Мотоциклы",
                        imageUrl = "https://sun9-31.userapi.com/impg/Wbs1xEG3HRGpE5cLzvcYoew6wdIMOmGh6zLx0w/l1TvUKhEj_0.jpg?size=604x601&quality=95&sign=f8540decdfb911ad49fe406c489b10e0&c_uniq_tag=v6Oh0YHAJ9y-RWTkAnyhAFanOZSw7SX6DmY1k9dAXqw&type=album",
                        onCardClick = { navController.navigate(MOTORCYCLE) }
                    )
                }
            }
        }
    }
}
