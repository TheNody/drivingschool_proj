package com.example.drivingschool76.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivingschool76.data.UIEvent.SignupUIEvent
import com.example.drivingschool76.data.UIState.RegistrationUIState
import com.example.drivingschool76.data.network.SupabaseClient.client
import com.example.drivingschool76.data.rules.Validator
import com.example.drivingschool76.data.userRepository.model.User
import com.example.drivingschool76.utils.AuthTokenManager
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val idrole = 1

    private val TAG = SignupViewModel::class.simpleName

    private val authTokenManager = AuthTokenManager(application.applicationContext)

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )


        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }

    private fun saveToken() {
        val accessToken = client.auth.currentAccessTokenOrNull()
        authTokenManager.saveToken(accessToken)
    }

    private fun signUp() {
        Log.e(TAG, "Inside_signUp")
        printState()
        viewModelScope.launch {
            try {
                signUpInProgress.value = true

                client.auth.signUpWith(Email) {
                    email = registrationUIState.value.email
                    password = registrationUIState.value.password
                }

                val user = User(
                    email = registrationUIState.value.email,
                    firstName = registrationUIState.value.firstName,
                    lastName = registrationUIState.value.lastName,
                    phonenumber = "",
                    surname = "",
                    role_id = idrole
                )

                client.postgrest
                    .from("Users")
                    .insert(user)
                saveToken()
                signUpInProgress.value = false
                Log.d(TAG, "Registered user successfully!")
            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
                Log.e(TAG, "Inside_OnFailureListener")
                signUpInProgress.value = false
            }
        }
    }
}