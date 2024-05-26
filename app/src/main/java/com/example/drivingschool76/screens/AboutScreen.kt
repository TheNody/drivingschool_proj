package com.example.drivingschool76.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.components.SwipeableTextCards
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import kotlinx.coroutines.launch

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val visible = remember { mutableStateOf(true) }
    val userRoleState by loginViewModel.userStateRole.collectAsState()


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
            containerColor = Color.Transparent,
            topBar = {
                AppToolbar(
                    title = stringResource(R.string.about_us),
                    navController = navController,
                    navigationIconClicked = {
                    coroutineScope.launch {
                        drawerState.open()
                        visible.value = false
                        }
                    }
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
            Box(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {

                SwipeableTextCards(Modifier.align(Alignment.Center))

            }
        }
    }
}
