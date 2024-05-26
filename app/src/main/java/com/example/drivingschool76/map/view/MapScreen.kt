package com.example.drivingschool76.map.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.drivingschool76.R
import com.example.drivingschool76.components.AppToolbar
import com.example.drivingschool76.components.BottomSheet
import com.example.drivingschool76.components.CustomBottomBar
import com.example.drivingschool76.components.InstructorBottomBar
import com.example.drivingschool76.components.ManagerBottomBar
import com.example.drivingschool76.components.NavDrawer
import com.example.drivingschool76.components.rememberMapViewWithLifecycle
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.map.viewModel.MapViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = viewModel(),
    loginViewModel: LoginViewModel
) {
    val context = LocalContext.current
    val mapView = rememberMapViewWithLifecycle()
    val showSheet by viewModel.showSheet.collectAsState()
    val userRoleState by loginViewModel.userStateRole.collectAsState()

    if (showSheet) {
        BottomSheet(
            onDismiss = {
                viewModel.onHideBottomSheet()
            },
            viewModel = viewModel)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val visible = remember { mutableStateOf(true) }
    val density = LocalDensity.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            NavDrawer(
                navController = navController,
                drawerState = drawerState,
                loginView = loginViewModel
            )
        }
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                AnimatedVisibility(
                    visible = visible.value,
                    enter = slideInVertically { with(density) { -40.dp.roundToPx() } } + expandVertically() + fadeIn(),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    AppToolbar(
                        title = stringResource(R.string.map),
                        navController = navController,
                        navigationIconClicked = {
                            coroutineScope.launch {
                                drawerState.open()
                                visible.value = false
                            }
                        }
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = visible.value,
                    enter = slideInVertically { with(density) { 40.dp.roundToPx() } } + expandVertically() + fadeIn(),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    when (userRoleState) {
                        UserRoleState.User -> CustomBottomBar(navController)
                        UserRoleState.Instructor -> InstructorBottomBar(navController)
                        UserRoleState.Manager -> ManagerBottomBar(navController)
                        UserRoleState.Authenticated -> CustomBottomBar(navController)
                        else -> {}
                    }
                }
            }
        ) {
            AndroidView(
                factory = { mapView },
                modifier = Modifier.fillMaxSize(),
                update = { mapView ->
                    val mapObjects = mapView.mapWindow.map.mapObjects.addCollection()
                    viewModel.setupPlacemarks(mapObjects, context)
                }
            )
        }
    }
}


