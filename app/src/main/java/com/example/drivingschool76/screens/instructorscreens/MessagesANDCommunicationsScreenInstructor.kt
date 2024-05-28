@file:OptIn(ExperimentalFoundationApi::class)

package com.example.drivingschool76.screens.instructorscreens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.chat.viewmodel.ChatViewModel
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.ChatListItem
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import kotlinx.coroutines.launch

@Composable
fun MessagesANDCommunicationsScreenInstructor(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel,
    chatViewModel: ChatViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val userRoleState by loginViewModel.userStateRole.collectAsState()
    val messages by chatViewModel.messages.collectAsState()
    val isCollapsed = remember { mutableStateOf(false) }
    val isGeneralChatsCollapsed = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val selectedChatId = remember { mutableStateOf<String?>(null) }

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
                    title = stringResource(R.string.chat),
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
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    stickyHeader {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { isCollapsed.value = !isCollapsed.value }
                                    .padding(16.dp),
                            ) {
                                Icon(
                                    imageVector = if (isCollapsed.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Чат с учениками",
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier
                                        .weight(2f)
                                        .align(Alignment.CenterVertically),
                                )
                                Icon(
                                    imageVector = if (isCollapsed.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Divider(color = Color.Gray, thickness = 0.5.dp)
                    }
                    if (!isCollapsed.value) {
                        items(messages) { message ->
                            ChatListItem(
                                chatMessage = message,
                                onClick = {
                                    selectedChatId.value = message.chatId
                                    navController.navigate("view_chat_screen/${message.chatId}")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(if (selectedChatId.value == message.chatId) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent)
                                    .animateContentSize()
                            )
                            Divider(color = Color.Gray, thickness = 0.5.dp)
                        }
                    }

                    stickyHeader {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.surface
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { isGeneralChatsCollapsed.value = !isGeneralChatsCollapsed.value }
                                    .padding(16.dp),
                            ) {
                                Icon(
                                    imageVector = if (isGeneralChatsCollapsed.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Общие чаты",
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier
                                        .weight(2f)
                                        .align(Alignment.CenterVertically),
                                )
                                Icon(
                                    imageVector = if (isGeneralChatsCollapsed.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Divider(color = Color.Gray, thickness = 0.5.dp)
                    }
                    if (!isGeneralChatsCollapsed.value) {
                        items(messages) { message ->
                            ChatListItem(
                                chatMessage = message,
                                onClick = {
                                    selectedChatId.value = message.chatId
                                    navController.navigate("view_chat_screen/${message.chatId}")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(if (selectedChatId.value == message.chatId) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent)
                                    .animateContentSize()
                            )
                            Divider(color = Color.Gray, thickness = 0.5.dp)
                        }
                    }
                }
            }
        }
    }
}








