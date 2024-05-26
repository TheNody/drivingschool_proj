package com.example.drivingschool76.screens.instructorscreens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.CarBrandInput
import com.example.drivingschool76.components.CarModelInput
import com.example.drivingschool76.components.ConfirmationButton
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.DividerTextComponent
import com.example.drivingschool76.components.ImagePicker
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.components.RussianLicensePlate
import com.example.drivingschool76.data.viewmodel.CarInformationViewModel
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import kotlinx.coroutines.launch

@Composable
fun InformationCarScreenInstructor(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    val carInformationViewModel: CarInformationViewModel = viewModel()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val userRoleState by loginViewModel.userStateRole.collectAsState()
    val showDialog by carInformationViewModel.showDialog.observeAsState(false)
    val imageUris by remember { mutableStateOf(listOf<Uri>()) }

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
                    title = stringResource(R.string.information_car),
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
                    else -> {}
                }
            }
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                DividerTextComponent(text = "ИНФОРМАЦИЯ О ТРАНСПОРТЕ")
                if (showDialog) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Информация о машине обновлена успешно", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                RussianLicensePlate(carInformationViewModel)
                Spacer(modifier = Modifier.height(16.dp))
                CarBrandInput(carInformationViewModel)
                Spacer(modifier = Modifier.height(16.dp))
                CarModelInput(carInformationViewModel)
                Spacer(modifier = Modifier.height(16.dp))
                DividerTextComponent(text = "НЕОБЯЗАТЕЛЬНАЯ ИНФОРМАЦИЯ")
                ImagePicker()
                Spacer(modifier = Modifier.height(12.dp))
                ConfirmationButton(
                    viewModel = carInformationViewModel,
                    scope = coroutineScope,
                    imageUris = imageUris,
                )
            }
        }
    }
}


