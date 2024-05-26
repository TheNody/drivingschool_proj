package com.example.drivingschool76.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.ButtonComponent
import com.example.drivingschool76.components.ClickableLoginTextComponent
import com.example.drivingschool76.components.DividerTextComponent
import com.example.drivingschool76.components.HeadingTextComponents
import com.example.drivingschool76.components.MyTextFieldComponent
import com.example.drivingschool76.components.NormalTextComponents
import com.example.drivingschool76.components.PasswordTextFieldComponent
import com.example.drivingschool76.components.UnderLineNormalTextComponents
import com.example.drivingschool76.data.UIEvent.LoginUIEvent
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.utils.HOME_SCREEN
import com.example.drivingschool76.utils.MAIN_SCREEN_INSTRUCTOR_SCREEN
import com.example.drivingschool76.utils.MANAGER_SCREEN
import com.example.drivingschool76.utils.REGISTER_SCREEN

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val userRoleState by loginViewModel.userStateRole.collectAsState()
    val allValidationsPassed = loginViewModel.allValidationsPassed

    LaunchedEffect(userRoleState) {
        when (userRoleState) {
            UserRoleState.Instructor -> {
                navController.navigate(MAIN_SCREEN_INSTRUCTOR_SCREEN) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
            UserRoleState.Manager -> {
                navController.navigate(MANAGER_SCREEN) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
            UserRoleState.User, UserRoleState.Authenticated -> {
                navController.navigate(HOME_SCREEN) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            }
            UserRoleState.Unloggining -> {
                // No action required
            }
        }
    }

    if (userRoleState == UserRoleState.Unloggining) {
        LoginContent(navController, loginViewModel, allValidationsPassed.value)
    }
}

@Composable
private fun LoginContent(
    navController: NavController,
    loginViewModel: LoginViewModel,
    allValidationsPassed: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                NormalTextComponents(value = stringResource(id = R.string.welcome_back))
                HeadingTextComponents(value = stringResource(id = R.string.login))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = { loginViewModel.onEvent(LoginUIEvent.EmailChanged(it)) },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = { loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it)) },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                UnderLineNormalTextComponents(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        if (allValidationsPassed) {
                            loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                        }
                    },
                    isEnabled = allValidationsPassed
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent(text = "или")

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate(REGISTER_SCREEN)
                })
            }
        }
    }
}


