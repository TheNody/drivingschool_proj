package com.example.drivingschool76.utils

import java.time.LocalDate
import java.time.LocalDateTime


data class Appointment(
    val id: Int,
    val name: String,
    val dateTime: LocalDateTime,
    val phone: String,
    val email: String,
    val imageUrl: String
)

data class DailyAppointments(
    val date: LocalDate,
    val appointments: List<Appointment>
)