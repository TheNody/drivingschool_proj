package com.example.drivingschool76.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.CircleWithLink
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.CustomDropdownMenu
import com.example.drivingschool76.components.CustomDropdownMenuForMiddleName
import com.example.drivingschool76.components.DividerTextComponent
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.components.NotificationSwitch
import com.example.drivingschool76.components.ShuffleAnswersRow
import com.example.drivingschool76.components.ThemeChangeSwitch
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.utils.DropDownMenu
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel,
    onSwitchTheme: (Boolean) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val userRoleState by loginViewModel.userStateRole.collectAsState()

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
                    title = stringResource(R.string.settings),
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
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .statusBarsPadding()
                    .verticalScroll(scrollState)
                    .navigationBarsPadding()
            ) {
                DividerTextComponent(text = "ПРОФИЛЬ ПОЛЬЗОВАТЕЛЯ")

                val currentMenu = remember { mutableStateOf<DropDownMenu?>(null) }

                CustomDropdownMenu(
                    buttonText = "Изменить имя",
                    changeLabel = "Введите новое имя",
                    changeAccept = "Применить изменения",
                    isOpen = currentMenu.value == DropDownMenu.CHANGE_NAME,
                    onClick = {
                        if (currentMenu.value == DropDownMenu.CHANGE_NAME) {
                            currentMenu.value = null
                        } else {
                            currentMenu.value = DropDownMenu.CHANGE_NAME
                        }
                    },
                    onClose = {
                        currentMenu.value = null
                    }
                )

                CustomDropdownMenu(
                    buttonText = "Изменить фамилию",
                    changeLabel = "Введите новую фамилию",
                    changeAccept = "Применить изменения",
                    isOpen = currentMenu.value == DropDownMenu.CHANGE_LAST_NAME,
                    onClick = {
                        if (currentMenu.value == DropDownMenu.CHANGE_LAST_NAME) {
                            currentMenu.value = null
                        } else {
                            currentMenu.value = DropDownMenu.CHANGE_LAST_NAME
                        }
                    },
                    onClose = {
                        currentMenu.value = null
                    }
                )

                CustomDropdownMenu(
                    buttonText = "Изменить пароль",
                    changeLabel = "Введите новый пароль",
                    changeAccept = "Применить изменения",
                    isOpen = currentMenu.value == DropDownMenu.CHANGE_PASSWORD,
                    onClick = {
                        if (currentMenu.value == DropDownMenu.CHANGE_PASSWORD) {
                            currentMenu.value = null
                        } else {
                            currentMenu.value = DropDownMenu.CHANGE_PASSWORD
                        }
                    },
                    onClose = {
                        currentMenu.value = null
                    }
                )

                CustomDropdownMenuForMiddleName(
                    buttonText = "Добавить/изменить отчество (при наличии)",
                    changeLabel = "Добавьте/измените отчество",
                    changeAccept = "Применить изменения",
                    isOpen = currentMenu.value == DropDownMenu.CHANGE_MIDDLE_NAME,
                    onClick = {
                        if (currentMenu.value == DropDownMenu.CHANGE_MIDDLE_NAME) {
                            currentMenu.value = null
                        } else {
                            currentMenu.value = DropDownMenu.CHANGE_MIDDLE_NAME
                        }
                    },
                    onClose = {
                        currentMenu.value = null
                    }
                )

                Spacer(modifier = Modifier.height(7.dp))

                DividerTextComponent(text = "ОБЩИЕ НАСТРОЙКИ")

                NotificationSwitch()

                ThemeChangeSwitch(
                    context = context,
                    buttonText = "Сменить тему приложения",
                    key = "unique_key_for_theme_switch",
                    onSwitchChanged = onSwitchTheme
                )

                DividerTextComponent(text = "НАСТРОЙКИ ДЛЯ БИЛЕТОВ")

                ShuffleAnswersRow(
                    context = context,
                    buttonText = "Перемешать ответы",
                    key = "unique_key_for_switch_state",
                    onShuffleClicked = { /* Код для обработки клика на Box */ },
                    onSwitchChanged = { /* Код для обработки изменения Switch */ }
                )

                DividerTextComponent(text = "СООБЩИТЬ ОБ ОШИБКАХ")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircleWithLink(
                        imagePainter = painterResource(id = R.drawable.instagram),
                        url = "https://www.instagram.com/avtoshkola_76"
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    CircleWithLink(
                        imagePainter = painterResource(id = R.drawable.vk),
                        url = "https://vk.com/im?media=&sel=-103651724"
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    CircleWithLink(
                        imagePainter = painterResource(id = R.drawable.telegram),
                        url = "https://t.me/avtoshkola76yar_bot"
                    )
                }

                DividerTextComponent(text = stringResource(id = R.string.or))
            }
        }
    }
}





