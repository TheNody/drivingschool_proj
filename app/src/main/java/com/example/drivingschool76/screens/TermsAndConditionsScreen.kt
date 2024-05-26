package com.example.drivingschool76.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.HeadingTextComponents
import com.example.drivingschool76.navigation.SystemBackButtonHandler
import com.example.drivingschool76.utils.REGISTER_SCREEN

@Composable
fun TermsAndConditionsScreen(navController: NavHostController) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)) {

        HeadingTextComponents(value = stringResource(id = R.string.terms_and_conditions))

    }

    SystemBackButtonHandler {
        navController.navigate(REGISTER_SCREEN)
    }

}