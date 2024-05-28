package com.example.drivingschool76.utils

import java.time.LocalDate
import java.time.LocalTime

data class DatePickerClass(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val date: LocalDate,
    var isSelected: Boolean = false
)
