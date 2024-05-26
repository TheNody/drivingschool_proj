package com.example.drivingschool76.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.drivingschool76.utils.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    init {
        loadMessages()
    }

    private fun loadMessages() {
        _messages.value = listOf(
            ChatMessage(1, "Test Test", "path_to_avatar", "20:50", "Hi, where are you?"),
            ChatMessage(2, "Test1 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(3, "Test2 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(4, "Test3 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(5, "Test4 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(6, "Test5 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(7, "Test6 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(8, "Test7 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(9, "Test8 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(10, "Test9 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(11, "Test10 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
            ChatMessage(12, "Test11 Test1", "path_to_avatar", "20:49", "Has anyone encountered this issue?"),
        )
    }
}

