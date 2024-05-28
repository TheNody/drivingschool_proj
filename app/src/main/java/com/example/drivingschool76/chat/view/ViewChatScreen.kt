package com.example.drivingschool76.chat.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.drivingschool76.chat.component.ChatMessageCard
import com.example.drivingschool76.chat.component.CustomBottomBar
import com.example.drivingschool76.chat.viewmodel.ChatViewModel
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.utils.CHAT_SCREEN
import com.example.drivingschool76.utils.MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel,
    loginViewModel: LoginViewModel,
    chatId: String
) {
    val messages by chatViewModel.messages.collectAsState()
    val chatMessages = messages.filter { it.chatId == chatId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            when (loginViewModel.userStateRole.value) {
                                UserRoleState.Instructor -> navController.navigate(MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN)
                                UserRoleState.User -> navController.navigate(CHAT_SCREEN)
                                UserRoleState.Manager -> {/* Добавить навигацию для Manager */}
                                else -> {}
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Go Back",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        val firstMessage = chatMessages.firstOrNull()
                        Image(
                            painter = rememberAsyncImagePainter(model = firstMessage?.avatar ?: "path_to_default_avatar"),
                            contentDescription = "Chat Partner Avatar",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .border(0.5.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(firstMessage?.username ?: "Unknown")
                    }
                }
            )
        },
        bottomBar = {
            CustomBottomBar(
                chatViewModel = chatViewModel,
                chatId = chatId
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                reverseLayout = false
            ) {
                items(chatMessages) { message ->
                    ChatMessageCard(message)
                }
            }
        }
    }
}



