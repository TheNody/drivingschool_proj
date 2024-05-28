package com.example.drivingschool76.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.drivingschool76.R
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.ConfirmationDialog
import com.example.drivingschool76.components.ConfirmedAppointmentsList
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.DateColumn
import com.example.drivingschool76.components.DateSelectionButton
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.components.createTimeSlots
import com.example.drivingschool76.components.generateDates
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.utils.DatePickerClass
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val userRoleState by loginViewModel.userStateRole.collectAsState()

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dates by remember { derivedStateOf { generateDates(selectedDate) } }
    var selectedSlots by remember { mutableStateOf(createTimeSlots(dates)) }
    val pagerState = rememberPagerState()

    var anySlotSelected by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var confirmedAppointments by remember { mutableStateOf<Map<LocalDate, List<DatePickerClass>>>(emptyMap()) }

    // Пример данных об инструкторе
    val instructorName = "Иванов Иван Иванович"
    val carBrand = "Toyota"
    val carModel = "Camry"
    val letter1 = "А"
    val numbers = "123"
    val letter2 = "БВ"
    val regionPart = "77"
    val instructorImageUrl = "https://example.com/instructor.jpg"

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavDrawer(
                navController = navController,
                drawerState = drawerState,
                loginView = loginViewModel
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = stringResource(R.string.account),
                    navController = navController,
                    navigationIconClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                when (userRoleState) {
                    UserRoleState.User -> CustomBottomBar(navController)
                    UserRoleState.Instructor -> InstructorBottomBar(navController)
                    UserRoleState.Manager -> ManagerBottomBar(navController)
                    UserRoleState.Authenticated -> CustomBottomBar(navController)
                    else -> {}
                }
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Divider(color = Color.LightGray, thickness = 0.5.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(instructorImageUrl),
                            contentDescription = "User Avatar",
                            modifier = Modifier
                                .size(80.dp)
                                .border(1.dp, Color.LightGray, CircleShape)
                        )
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = instructorName,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "$carBrand $carModel ; $letter1 $numbers $letter2 $regionPart ",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }
                    Divider(color = Color.LightGray, thickness = 0.5.dp)
                }
                item {
                    DateSelectionButton(selectedDate) { newDate ->
                        selectedDate = newDate
                        selectedSlots = createTimeSlots(generateDates(newDate))
                        coroutineScope.launch {
                            pagerState.scrollToPage(0)
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        HorizontalPager(
                            count = dates.size,
                            state = pagerState,
                            itemSpacing = 8.dp,
                            contentPadding = PaddingValues(horizontal = 32.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) { page ->
                            DateColumn(
                                date = dates[page],
                                slots = selectedSlots.filter { it.date == dates[page] },
                                onSlotSelected = { slot, isSelected ->
                                    selectedSlots = selectedSlots.map {
                                        if (it == slot) it.copy(isSelected = isSelected) else it
                                    }
                                    anySlotSelected = selectedSlots.any { it.isSelected }
                                }
                            )
                        }
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            },
                            enabled = pagerState.currentPage > 0,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .background(Color.Transparent)
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Previous Day")
                        }
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                            enabled = pagerState.currentPage < dates.size - 1,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .background(Color.Transparent)
                        ) {
                            Icon(Icons.Default.ArrowForward, contentDescription = "Next Day")
                        }
                    }
                }
                if (anySlotSelected) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                showConfirmationDialog = true
                            }
                        ) {
                            Text("Записаться на занятие")
                        }
                    }
                }
                item {
                    ConfirmedAppointmentsList(confirmedAppointments)
                }
            }
            if (showConfirmationDialog) {
                ConfirmationDialog(
                    selectedSlots = selectedSlots.filter { it.isSelected },
                    onConfirm = {
                        showConfirmationDialog = false
                        val newAppointments = selectedSlots.filter { it.isSelected }.groupBy { it.date }
                        confirmedAppointments = confirmedAppointments + newAppointments
                        selectedSlots = selectedSlots.map { it.copy(isSelected = false) }
                        anySlotSelected = false
                    },
                    onDismiss = {
                        showConfirmationDialog = false
                    }
                )
            }
        }
    }
}
