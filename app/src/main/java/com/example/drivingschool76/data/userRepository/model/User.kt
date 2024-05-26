package com.example.drivingschool76.data.userRepository.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val surname: String? = "",
    val phonenumber: String? = "",
    val role_id: Int
)
