package com.example.drivingschool76.screens.instructorscreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.components.UserDetailDialog
import com.example.drivingschool76.components.UserListItem
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.SearchViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.utils.UserInfoCar
import kotlinx.coroutines.launch

@Composable
fun ProfilesStudentsInstructorScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    searchViewModel: SearchViewModel,
    loginViewModel: LoginViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val userRoleState by loginViewModel.userStateRole.collectAsState()
    val users by searchViewModel.users.collectAsState()
    val selectedUser = remember { mutableStateOf<UserInfoCar?>(null) }


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
                    title = stringResource(R.string.profile_students),
                    navController = navController,
                    navigationIconClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                when (userRoleState) {
                    UserRoleState.User -> CustomBottomBar(navController)
                    UserRoleState.Instructor -> InstructorBottomBar(navController)
                    UserRoleState.Manager -> ManagerBottomBar(navController)
                    else -> {}
                }
            }
        ) { innerPadding ->
            Box(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(users) { user ->
                        UserListItem(user = user) {
                            selectedUser.value = user
                        }
                        Divider(color = Color.Gray, thickness = 0.5.dp, modifier = Modifier.fillMaxWidth())
                    }
                }
                selectedUser.value?.let { user ->
                    UserDetailDialog(user = user, onDismiss = { selectedUser.value = null }, onNavigateToChat = {
                        navController.navigate("view_chat_screen/${user.id}")
                    })
                }
            }
        }
    }
}

