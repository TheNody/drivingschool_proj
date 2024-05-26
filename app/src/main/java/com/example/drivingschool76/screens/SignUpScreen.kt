package com.example.drivingschool76.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.ButtonComponent
import com.example.drivingschool76.components.CheckboxComponent
import com.example.drivingschool76.components.ClickableLoginTextComponent
import com.example.drivingschool76.components.DividerTextComponent
import com.example.drivingschool76.components.HeadingTextComponents
import com.example.drivingschool76.components.MyTextFieldComponent
import com.example.drivingschool76.components.NormalTextComponents
import com.example.drivingschool76.components.PasswordTextFieldComponent
import com.example.drivingschool76.data.UIEvent.SignupUIEvent
import com.example.drivingschool76.data.viewmodel.SignupViewModel
import com.example.drivingschool76.utils.LOGIN_SCREEN
import com.example.drivingschool76.utils.TERMS_SCREEN

@Composable
fun SignUpScreen(
    navController: NavController,
    signUpViewModel: SignupViewModel = viewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {}

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            NormalTextComponents(value = stringResource(id = R.string.hello))
            HeadingTextComponents(value = stringResource(id = R.string.createAccount))
            Spacer(modifier = Modifier.heightIn(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.firstName),
                painterResource(id = R.drawable.profile),
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.firstNameError
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.lastName),
                painterResource(id = R.drawable.profile),
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.lastNameError
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email),
                onTextChanged = {
                    signUpViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.emailError
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    signUpViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                },
                errorStatus = signUpViewModel.registrationUIState.value.passwordError
            )

            CheckboxComponent(onTextSelected = {
                navController.navigate(TERMS_SCREEN)
            },
                onCheckedChange = {
                    signUpViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = {
                    signUpViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                },
                isEnabled = signUpViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent(text = "или")

            ClickableLoginTextComponent(
                tryingToLogin = true, onTextSelected = {
                    navController.navigate(LOGIN_SCREEN)
                }
            )
        }
    }

//    if (signUpViewModel.signUpInProgress.value) {
//        CircularProgressIndicator()
//    }
}

