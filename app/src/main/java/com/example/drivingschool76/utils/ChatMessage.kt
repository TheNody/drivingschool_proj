package com.example.drivingschool76.utils

data class ChatMessage(
    val id: Int,
    val username: String,
    val avatar: String,
    val timestamp: String,
<<<<<<< HEAD
    val message: String
)

=======
    val message: String,
    val chatId: String,
    val isCurrentUser: Boolean = false
)
>>>>>>> b2ca48b (AddChat_alpha)
