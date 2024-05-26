package com.example.drivingschool76.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.drivingschool76.chat.view.ViewChatScreen
import com.example.drivingschool76.chat.viewmodel.ChatViewModel
import com.example.drivingschool76.components.SubscriptionRequiredScreen
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.SearchViewModel
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.map.view.MapScreen
import com.example.drivingschool76.screens.AboutScreen
import com.example.drivingschool76.screens.AccountScreen
import com.example.drivingschool76.screens.CategoryAMotorcycleScreen
import com.example.drivingschool76.screens.CategoryBAutomaticScreen
import com.example.drivingschool76.screens.CategoryBMechanicScreen
import com.example.drivingschool76.screens.ChatScreen
import com.example.drivingschool76.screens.HomeScreen
import com.example.drivingschool76.screens.LoginScreen
import com.example.drivingschool76.screens.ManagerScreen
import com.example.drivingschool76.screens.NotificationScreen
import com.example.drivingschool76.screens.SettingsScreen
import com.example.drivingschool76.screens.ShoppingCartScreen
import com.example.drivingschool76.screens.SignUpScreen
import com.example.drivingschool76.screens.TermsAndConditionsScreen
import com.example.drivingschool76.screens.instructorscreens.CalendarScreenInstructor
import com.example.drivingschool76.screens.instructorscreens.InformationCarScreenInstructor
import com.example.drivingschool76.screens.instructorscreens.MainScreenInstructor
import com.example.drivingschool76.screens.instructorscreens.MessagesANDCommunicationsScreenInstructor
import com.example.drivingschool76.screens.instructorscreens.ProfilesStudentsInstructorScreen

@Composable
fun NavController(
    startDestination: String = NavigationDestination.LoginScreenDestination.destination,
    onSwitchTheme: (Boolean) -> Unit
) {
    val loginViewModel: LoginViewModel = viewModel()
    val navController = rememberNavController()
    val userRoleState by loginViewModel.userStateRole.collectAsState()
    val chatViewModel: ChatViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(500)) + slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) + slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) + slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) + slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(500)) }
    ) {
        composable(NavigationDestination.LoginScreenDestination.destination) {
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }

        composable(NavigationDestination.RegistrationScreenDestination.destination) {
            SignUpScreen(navController = navController)
        }

        composable(NavigationDestination.HomeScreenDestination.destination) {
            HomeScreen(navController = navController, loginViewModel = loginViewModel)
        }

        composable(NavigationDestination.AccountScreenDestination.destination) {
            AccountScreen(navController = navController, loginViewModel = loginViewModel)
            if (userRoleState == UserRoleState.Authenticated) {
                SubscriptionRequiredScreen(navController = navController)
            }
        }

        composable(NavigationDestination.SearchScreenDestination.destination) {
            ChatScreen(navController = navController, loginViewModel = loginViewModel)
            if (userRoleState == UserRoleState.Authenticated) {
                SubscriptionRequiredScreen(navController = navController)
            }
        }

        composable(NavigationDestination.NotificationScreenDestination.destination) {
            NotificationScreen(navController = navController, loginViewModel = loginViewModel)
            if (userRoleState == UserRoleState.Authenticated) {
                SubscriptionRequiredScreen(navController = navController)
            }
        }

        composable(NavigationDestination.SettingsScreenDestination.destination) {
            SettingsScreen(navController = navController, loginViewModel = loginViewModel, onSwitchTheme = onSwitchTheme)
        }

        composable(NavigationDestination.TermsScreenDestination.destination) {
            TermsAndConditionsScreen(navController = navController)
        }

        composable(NavigationDestination.MapScreenDestination.destination) {
            MapScreen(navController = navController, loginViewModel = loginViewModel)
        }

        composable(NavigationDestination.AboutScreenDestination.destination) {
            AboutScreen(navController = navController, loginViewModel = loginViewModel)
        }

        composable(NavigationDestination.ShoppingCartScreenDestination.destination) {
            ShoppingCartScreen(navController = navController, loginViewModel = loginViewModel)
        }

        composable(NavigationDestination.CategoryBAutomaticScreenDestination.destination) {
            CategoryBAutomaticScreen(navController = navController)
        }

        composable(NavigationDestination.CategoryAMotorcycleScreenDestination.destination) {
            CategoryAMotorcycleScreen(navController = navController)
        }

        composable(NavigationDestination.CategoryBMechanicScreenDestination.destination) {
            CategoryBMechanicScreen(navController = navController)
        }



        // Instructor Screen's
        composable(NavigationDestination.ProfilesStudentsInstructorDestination.destination) {
            if (userRoleState == UserRoleState.Instructor) {
                ProfilesStudentsInstructorScreen(navController = navController, loginViewModel = loginViewModel, searchViewModel = searchViewModel)
            }
        }

        composable(NavigationDestination.MessagesAndCommunicationsInstructorDestination.destination) {
            if (userRoleState == UserRoleState.Instructor) {
                MessagesANDCommunicationsScreenInstructor(navController = navController, loginViewModel = loginViewModel, chatViewModel = chatViewModel)
            }
        }

        composable(NavigationDestination.CalendarInstructorDestination.destination) {
            if (userRoleState == UserRoleState.Instructor) {
                CalendarScreenInstructor(navController = navController, loginViewModel = loginViewModel)
            }
        }

        composable(NavigationDestination.MainScreenInstructorDestination.destination) {
            if (userRoleState == UserRoleState.Instructor) {
                MainScreenInstructor(navController = navController, loginViewModel = loginViewModel)
            }
        }

        composable(NavigationDestination.InfromationCarScreenInstructorDestination.destination) {
            if (userRoleState == UserRoleState.Instructor) {
                InformationCarScreenInstructor(navController = navController, loginViewModel = loginViewModel)
            } 
        }

        // Manager Screen's
        composable(NavigationDestination.ManagerScreenDestination.destination) {
            ManagerScreen(navController = navController, loginViewModel = loginViewModel)
        }


        //ViewChatScreen

        composable(
            route = "view_chat_screen/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.StringType })
        ) { backStackEntry ->
            ViewChatScreen(
                navController = navController,
                chatId = backStackEntry.arguments?.getString("chatId") ?: "",
                chatViewModel = chatViewModel,
                loginViewModel = loginViewModel
            )
        }
    }
}