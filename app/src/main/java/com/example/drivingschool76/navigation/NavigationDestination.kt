package com.example.drivingschool76.navigation

import com.example.drivingschool76.utils.ABOUT_SCREEN
import com.example.drivingschool76.utils.ACCOUNT_SCREEN
import com.example.drivingschool76.utils.CALENDAR_INSTRUCTOR_DESTINATION_SCREEN
import com.example.drivingschool76.utils.CATEGORYB_AUTH
import com.example.drivingschool76.utils.CATEGORYB_MECH
import com.example.drivingschool76.utils.HOME_SCREEN
import com.example.drivingschool76.utils.INFORMATION_ABOUT_CAR_SCREEN
import com.example.drivingschool76.utils.LOGIN_SCREEN
import com.example.drivingschool76.utils.MAIN_SCREEN_INSTRUCTOR_SCREEN
import com.example.drivingschool76.utils.MANAGER_SCREEN
import com.example.drivingschool76.utils.MAP_SCREEN
import com.example.drivingschool76.utils.MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN
import com.example.drivingschool76.utils.MOTORCYCLE
import com.example.drivingschool76.utils.NOTIFICATION_SCREEN
import com.example.drivingschool76.utils.PROFILES_STUDENTS_INSTRUCTOR_SCREEN
import com.example.drivingschool76.utils.REGISTER_SCREEN
import com.example.drivingschool76.utils.SEARCH_SCREEN
import com.example.drivingschool76.utils.SETTINGS_SCREEN
import com.example.drivingschool76.utils.SHOPPINGCART_SCREEN
import com.example.drivingschool76.utils.TERMS_SCREEN

sealed class NavigationDestination (val destination: String){
    data object LoginScreenDestination:NavigationDestination(LOGIN_SCREEN)
    data object RegistrationScreenDestination:NavigationDestination(REGISTER_SCREEN)
    data object HomeScreenDestination:NavigationDestination(HOME_SCREEN)
    data object AccountScreenDestination:NavigationDestination(ACCOUNT_SCREEN)
    data object SearchScreenDestination:NavigationDestination(SEARCH_SCREEN)
    data object SettingsScreenDestination:NavigationDestination(SETTINGS_SCREEN)
    data object TermsScreenDestination:NavigationDestination(TERMS_SCREEN)
    data object MapScreenDestination:NavigationDestination(MAP_SCREEN)
    data object AboutScreenDestination:NavigationDestination(ABOUT_SCREEN)
    data object ManagerScreenDestination:NavigationDestination(MANAGER_SCREEN)
    data object NotificationScreenDestination:NavigationDestination(NOTIFICATION_SCREEN)
    data object ShoppingCartScreenDestination:NavigationDestination(SHOPPINGCART_SCREEN)
    data object MainScreenInstructorDestination:NavigationDestination(MAIN_SCREEN_INSTRUCTOR_SCREEN)
    data object ProfilesStudentsInstructorDestination:NavigationDestination(PROFILES_STUDENTS_INSTRUCTOR_SCREEN)
    data object MessagesAndCommunicationsInstructorDestination:NavigationDestination(MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN)
    data object CalendarInstructorDestination:NavigationDestination(CALENDAR_INSTRUCTOR_DESTINATION_SCREEN)
    data object InfromationCarScreenInstructorDestination:NavigationDestination(INFORMATION_ABOUT_CAR_SCREEN)
    data object CategoryBAutomaticScreenDestination:NavigationDestination(CATEGORYB_AUTH)
    data object CategoryBMechanicScreenDestination:NavigationDestination(CATEGORYB_MECH)
    data object CategoryAMotorcycleScreenDestination:NavigationDestination(MOTORCYCLE)
}
