package com.example.drivingschool76

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.drivingschool76.navigation.NavController
import com.example.drivingschool76.ui.theme.DrivingSchoolTheme
import com.example.drivingschool76.utils.SharedPreferenceHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val preferenceHelper = SharedPreferenceHelper(this)
            var isNightMode by remember {
                mutableStateOf(
                    preferenceHelper.getSwitchState(this, "theme_state")
                )
            }

            isNightMode = preferenceHelper.getSwitchState(this, "theme_state")

            val switchTheme: (Boolean) -> Unit = { isNight ->
                isNightMode = isNight
                preferenceHelper.saveSwitchState(this, "theme_state", isNight)
            }

            DrivingSchoolTheme(isNightMode = isNightMode) {
                NavController(
                    onSwitchTheme = switchTheme
                )
            }
        }
    }
}

