package com.example.drivingschool76.chat.model

import android.net.Uri

data class ChatMessage(
    val id: Int,
    val username: String,
    val avatar: String,
    val timestamp: String,
    val message: String,
    val chatId: String,
    val isCurrentUser: Boolean,
    val fileUris: List<Uri> = emptyList()

)

