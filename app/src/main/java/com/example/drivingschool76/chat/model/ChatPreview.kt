package com.example.drivingschool76.chat.model

data class ChatPreview(
    val id: String,
    val username: String,
    val avatar: String,
    var lastMessage: String,
    var lastMessageTime: String
)

