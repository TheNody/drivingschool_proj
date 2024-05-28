package com.example.drivingschool76.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.drivingschool76.utils.Appointment
import com.example.drivingschool76.utils.DailyAppointments
import java.time.LocalDate
import java.time.LocalDateTime


class ScheduleViewModel : ViewModel() {
    private val allAppointments = listOf(
        Appointment(1, "Alice", LocalDateTime.of(2024, 6, 15, 14, 0), "1234567890", "alice@example.com", "image_url_1"),
        Appointment(2, "Bob", LocalDateTime.of(2024, 6, 16, 16, 30), "1234567891", "bob@example.com", "image_url_2"),
        Appointment(3, "Charlie", LocalDateTime.of(2024, 6, 17, 10, 45), "1234567892", "charlie@example.com", "image_url_3"),
        Appointment(4, "Dave", LocalDateTime.of(2024, 6, 15, 9, 30), "1234567893", "dave@example.com", "image_url_4"),
        Appointment(5, "Eve", LocalDateTime.of(2024, 6, 16, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(6, "Eve", LocalDateTime.of(2024, 6, 16, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(7, "Eve", LocalDateTime.of(2024, 6, 17, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(8, "Eve", LocalDateTime.of(2024, 6, 18, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(9, "Eve", LocalDateTime.of(2024, 6, 19, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(10, "Eve", LocalDateTime.of(2024, 5, 27, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(11, "Eve", LocalDateTime.of(2024, 5, 27, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(13, "Eve", LocalDateTime.of(2024, 5, 28, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(14, "Eve", LocalDateTime.of(2024, 5, 28, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(15, "Eve", LocalDateTime.of(2024, 5, 28, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(16, "Eve", LocalDateTime.of(2024, 5, 28, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(17, "Eve", LocalDateTime.of(2024, 5, 28, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(18, "Eve", LocalDateTime.of(2024, 5, 28, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(19, "Eve", LocalDateTime.of(2024, 5, 27, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(20, "Eve", LocalDateTime.of(2024, 5, 22, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(21, "Eve", LocalDateTime.of(2024, 5, 27, 12, 0), "1234567894", "eve@example.com", "image_url_5"),
        Appointment(22, "Eve", LocalDateTime.of(2024, 5, 22, 11, 0), "1234567894", "eve@example.com", "image_url_5"),
    )

    fun getAppointmentsForPeriod(startDate: LocalDate, endDate: LocalDate): List<DailyAppointments> {
        return allAppointments
            .filter { it.dateTime.toLocalDate() in startDate..endDate }
            .groupBy { it.dateTime.toLocalDate() }
            .map { DailyAppointments(it.key, it.value) }
    }

    // Функция для получения занятий на сегодня
    fun getAppointmentsForToday(): List<Appointment> {
        val today = LocalDate.now()
        return allAppointments.filter { it.dateTime.toLocalDate() == today }
    }
}