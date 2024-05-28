package com.example.drivingschool76.chat.viewmodel

import androidx.lifecycle.ViewModel
import com.example.drivingschool76.chat.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    init {
        loadMessages()
    }

    private fun loadMessages() {

        _messages.value = listOf(
            ChatMessage(1, "Тест Тест", "path_to_avatar", "20:50", "Привет, где ты?", "chat1", false),
            ChatMessage(2, "Тест1 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat2", true),
            ChatMessage(3, "Тест2 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat3", true),
            ChatMessage(4, "Тест3 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat4", true),
            ChatMessage(5, "Тест4 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat5", true),
            ChatMessage(6, "Тест5 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat6", true),
            ChatMessage(7, "Тест6 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat7", true),
            ChatMessage(8, "Тест7 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat8", true),
            ChatMessage(9, "Тест8 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat9", true),
            ChatMessage(10, "Тест9 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat10", true),
            ChatMessage(11, "Тест10 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat11", true),
            ChatMessage(12, "Тест11 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat12", true),
            ChatMessage(13, "Тест12 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat13", true),
            ChatMessage(14, "Тест13 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat14", true),
            ChatMessage(15, "Тест14 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat15", true),
            ChatMessage(16, "Тест15 Тест1", "path_to_avatar", "20:49", "Кто-нибудь сталкивался с этой проблемой?", "chat16", true),
            // Добавьте другие тестовые сообщения по мере необходимости
        )
    }

    fun sendMessage(chatId: String, message: String) {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val currentUser = "Текущий Пользователь"
        val currentUserAvatar = "path_to_current_user_avatar"

        val newMessage = ChatMessage(
            id = messages.value.size + 1,
            username = currentUser,
            avatar = currentUserAvatar,
            timestamp = currentTime,
            message = message,
            chatId = chatId,
            isCurrentUser = true
        )
        _messages.value += newMessage
    }
}

