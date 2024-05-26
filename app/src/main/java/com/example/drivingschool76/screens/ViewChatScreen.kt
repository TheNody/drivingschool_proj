@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.drivingschool76.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.drivingschool76.data.viewmodel.ChatViewModel
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.utils.CHAT_SCREEN
import com.example.drivingschool76.utils.CHAT_SCREEN_FOR_MANAGER
import com.example.drivingschool76.utils.ChatMessage
import com.example.drivingschool76.utils.MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN

@Composable
fun ViewChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel,
    loginViewModel: LoginViewModel,
    chatId: String,
) {
    val messages by chatViewModel.messages.collectAsState()
    val chatMessages = messages.filter { it.chatId == chatId }
    val userRole by loginViewModel.userStateRole.collectAsState()

    var textState by remember { mutableStateOf("") }

    val chatPartner = chatMessages.firstOrNull()
    val chatPartnerName = chatPartner?.username ?: "Unknown"
    val chatPartnerAvatar = chatPartner?.avatar ?: "path_to_default_avatar"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            // Навигация в зависимости от роли пользователя
                            when (userRole) {
                                UserRoleState.Instructor -> navController.navigate(MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN)
                                UserRoleState.User -> navController.navigate(CHAT_SCREEN)
                                UserRoleState.Manager -> navController.navigate(CHAT_SCREEN_FOR_MANAGER)
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
                        Image(
                            painter = rememberAsyncImagePainter(model = chatPartnerAvatar),
                            contentDescription = "Chat Partner Avatar",
                            modifier = Modifier
                                .size(40.dp)
                                .border(0.5.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Chat with $chatPartnerName")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                TextField(
                    value = textState,
                    onValueChange = { textState = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Enter a message") },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (textState.isNotBlank()) {
                            chatViewModel.sendMessage(chatId, textState)
                            textState = ""  // Очистка поля ввода после отправки
                        }
                    }),
                    trailingIcon = {
                        IconButton(onClick = {
                            if (textState.isNotBlank()) {
                                chatViewModel.sendMessage(chatId, textState)
                                textState = ""
                            }
                        }) {
                            Icon(Icons.Filled.Send, contentDescription = "Send Message")
                        }
                    },
                    singleLine = true
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                reverseLayout = true
            ) {
                items(chatMessages) { message ->
                    ChatMessageCard(message)
                }
            }
        }
    }
}


@Composable
fun ChatMessageCard(message: ChatMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.isCurrentUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = if (message.isCurrentUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = message.username, style = MaterialTheme.typography.titleMedium)
                Text(text = message.message, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
