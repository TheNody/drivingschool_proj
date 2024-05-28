@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.drivingschool76.components

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ReadMore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.drivingschool76.R
import com.example.drivingschool76.chat.model.ChatMessage
import com.example.drivingschool76.data.userRepository.UserViewModel
import com.example.drivingschool76.data.userRepository.UserViewModelFactory
import com.example.drivingschool76.data.viewmodel.CarInformationViewModel
import com.example.drivingschool76.data.viewmodel.LoginViewModel
import com.example.drivingschool76.data.viewmodel.UIState
import com.example.drivingschool76.data.viewmodel.UserRoleState
import com.example.drivingschool76.map.model.PlacemarkContent
import com.example.drivingschool76.map.viewModel.MapViewModel
import com.example.drivingschool76.navigation.NavigationDestination
import com.example.drivingschool76.ui.theme.DarkBorderColor
import com.example.drivingschool76.ui.theme.DarkRedColor
import com.example.drivingschool76.ui.theme.SelectedBlue
import com.example.drivingschool76.utils.ACCOUNT_SCREEN
import com.example.drivingschool76.utils.Appointment
import com.example.drivingschool76.utils.AuthTokenManager
import com.example.drivingschool76.utils.CALENDAR_INSTRUCTOR_DESTINATION_SCREEN
import com.example.drivingschool76.utils.CHAT_SCREEN
import com.example.drivingschool76.utils.DailyAppointments
import com.example.drivingschool76.utils.DatePickerClass
import com.example.drivingschool76.utils.HOME_SCREEN
import com.example.drivingschool76.utils.INFORMATION_ABOUT_CAR_SCREEN
import com.example.drivingschool76.utils.LOGIN_SCREEN
import com.example.drivingschool76.utils.MAIN_SCREEN_INSTRUCTOR_SCREEN
import com.example.drivingschool76.utils.MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN
import com.example.drivingschool76.utils.NOTIFICATION_SCREEN
import com.example.drivingschool76.utils.PROFILES_STUDENTS_INSTRUCTOR_SCREEN
import com.example.drivingschool76.utils.SharedPreferenceHelper
import com.example.drivingschool76.utils.UserInfoCar
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import kotlin.math.abs

@Composable
fun NormalTextComponents(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            color = MaterialTheme.colorScheme.onBackground
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponents(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            color = MaterialTheme.colorScheme.onBackground
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun MyTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val textValue = remember {
        mutableStateOf("")
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            label = { Text(text = labelValue) },
            colors = outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                onTextChanged(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = null)
            },
            isError = errorStatus
        )
    }
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {
    val localFocusManager = LocalFocusManager.current

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            label = { Text(text = labelValue) },
            colors = outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardOptions.Default.keyboardType,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            keyboardActions = KeyboardActions {
                localFocusManager.clearFocus()
            },
            maxLines = 1,
            value = password.value,
            onValueChange = {
                password.value = it
                onTextSelected(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = null)
            },
            trailingIcon = {

                val iconImage = if (passwordVisible.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                val description = if (passwordVisible.value) {
                    stringResource(id = R.string.hide_password)
                } else {
                    stringResource(id = R.string.show_password)
                }

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }

            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            isError = errorStatus
        )
    }
}

@Composable
fun CheckboxComponent(
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val checkState = remember {
            mutableStateOf(false)
        }

        Checkbox(
            checked = checkState.value,
            onCheckedChange = {
                checkState.value = !checkState.value
                onCheckedChange.invoke(it)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onSurface,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        ClickableTextComponent(onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(onTextSelected: (String) -> Unit) {
    val initialText = "Продолжая, вы принимаете нашу "
    val privacyPolicyText = "Политику конфиденциальности"
    val andText = " и "
    val termsAndConditionsText = "Условия использования"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
            pop()
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
            append(andText)
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
            pop()
        }
    }

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{$span}")
                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }
        }
    )
}

@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            disabledContainerColor = Transparent
        ),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        )
                    ),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun DividerTextComponent(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText = if (tryingToLogin) "Есть учётная запись? " else "Ещё нет учётной записи? "
    val loginText = if (tryingToLogin) "Войти" else "Зарегистрироваться"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{$span}")

                if (span.item == loginText) {
                    onTextSelected(span.item)
                }
            }
        }
    )
}

@Composable
fun UnderLineNormalTextComponents(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        ),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun AppToolbar(
    title: String,
    navController: NavController,
    navigationIconClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) { Text(text = title, color = MaterialTheme.colorScheme.onSurface) }
        },

        navigationIcon = {
            IconButton(onClick = navigationIconClicked) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },

        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { navController.navigate("shoppingCartScreen") }) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "Shopping Cart Icon",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

@Composable
fun NavDrawer(
    navController: NavController,
    drawerState: DrawerState,
    loginView: LoginViewModel,
    context: Context = LocalContext.current
) {
    val authTokenManager = remember { AuthTokenManager(context) }
    val coroutineScope = rememberCoroutineScope()
    val currentRoute = navController.currentDestination?.route
    val userRoleState by loginView.userStateRole.collectAsState()

    val userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(context))
    val userState by userViewModel.userState.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.loadUser()
    }

    val isAnimationRunning by navController.currentBackStackEntryFlow
        .map { backStackEntry ->
            backStackEntry.arguments?.getBoolean("android-support-nav:controller:navigate:animator") ?: false
        }
        .collectAsState(initial = false)

    if (!isAnimationRunning) {
        ModalDrawerSheet {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "profile pic",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                                .align(Alignment.CenterVertically)
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .align(Alignment.CenterVertically),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "${userState?.firstName ?: "Unknown"} ${userState?.lastName ?: "User"}",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Text(
                                text = userState?.email ?: "unknown@example.com",
                                fontSize = 14.sp,
                                textAlign = TextAlign.Start,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                            )
                        }
                    }
                    Divider(
                        Modifier.align(Alignment.BottomCenter),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                    )
                }
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.settings), color = MaterialTheme.colorScheme.onSurface) },
                    selected = currentRoute == NavigationDestination.SettingsScreenDestination.destination,
                    onClick = {
                        navController.navigate(NavigationDestination.SettingsScreenDestination.destination)
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(id = R.string.settings),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.map), color = MaterialTheme.colorScheme.onSurface) },
                    selected = currentRoute == NavigationDestination.MapScreenDestination.destination,
                    onClick = {
                        navController.navigate(NavigationDestination.MapScreenDestination.destination)
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.GpsFixed,
                            contentDescription = stringResource(id = R.string.map),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.about_us), color = MaterialTheme.colorScheme.onSurface) },
                    selected = currentRoute == NavigationDestination.AboutScreenDestination.destination,
                    onClick = {
                        navController.navigate(NavigationDestination.AboutScreenDestination.destination)
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.ReadMore,
                            contentDescription = stringResource(id = R.string.about_us),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.logout), color = MaterialTheme.colorScheme.onSurface) },
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            authTokenManager.clearToken()
                            authTokenManager.saveRole("UNLOGGING")
                            loginView.reloadAll()
                            when (userRoleState) {
                                UserRoleState.Unloggining -> {
                                    println("in unlogging")
                                }
                                UserRoleState.Instructor -> {
                                    println("in Instructor")
                                }
                                UserRoleState.Manager -> {
                                    println("in Manager")
                                }
                                UserRoleState.User -> {
                                    println("in USER")
                                }
                                UserRoleState.Authenticated -> {
                                    println("in Authenticated")
                                }
                            }
                            navController.popBackStack(navController.graph.startDestinationId, inclusive = false)
                            navController.navigate(LOGIN_SCREEN)
                        }
                    },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = stringResource(id = R.string.logout),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun TicketButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent
        ),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                )
                .padding(vertical = 20.dp, horizontal = 30.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun RoundedRectangle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(250.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp), clip = true)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)),
                RoundedCornerShape(16.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ticket_image_light_theme),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Indicator1("Вопросы", "0/800")
                Indicator1("Билеты", "0/40")
                Indicator1("Темы", "0/27")
            }
        }
    }
}

@Composable
fun Indicator1(title: String, progress: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .size(width = 80.dp, height = 4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = progress,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ShuffleAnswersRow(
    context: Context,
    buttonText: String,
    onShuffleClicked: () -> Unit,
    onSwitchChanged: (Boolean) -> Unit,
    key: String = "shuffle_state"
) {
    val preferenceHelper = SharedPreferenceHelper(context)
    var isShuffled by remember { mutableStateOf(preferenceHelper.getSwitchState(context, key)) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                onShuffleClicked()
                isShuffled = !isShuffled
                preferenceHelper.saveSwitchState(context, key, isShuffled)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buttonText,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )

            Switch(
                checked = isShuffled,
                onCheckedChange = { newState ->
                    isShuffled = newState
                    preferenceHelper.saveSwitchState(context, key, newState)
                    onSwitchChanged(newState)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun CustomDropdownMenu(
    buttonText: String,
    changeLabel: String,
    changeAccept: String,
    isOpen: Boolean,
    onClick: () -> Unit,
    onClose: () -> Unit
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }

    if (isMenuExpanded != isOpen) {
        isMenuExpanded = isOpen
    }

    LaunchedEffect(isOpen) {
        isMenuExpanded = if (isOpen) {
            true
        } else {
            delay(100)
            false
        }
    }

    Button(
        onClick = {
            if (isMenuExpanded) {
                isMenuExpanded = false
                onClose()
            } else {
                isMenuExpanded = true
                onClick()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        contentPadding = PaddingValues(0.dp),
        elevation = if (isMenuExpanded) ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 16.dp) else ButtonDefaults.buttonElevation(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowUpward,
                    contentDescription = "Expand Menu",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            rotationZ = if (isMenuExpanded) 180f else 0f
                        }
                )
            }

            Spacer(Modifier.weight(1f))

            Text(
                text = buttonText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )

            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDownward,
                    contentDescription = "Collapse Menu",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            rotationZ = if (isMenuExpanded) 0f else 180f
                        }
                )
            }
        }
    }

    if (isMenuExpanded) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(8.dp))
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = changeLabel, fontSize = 24.sp, color = MaterialTheme.colorScheme.onSurface) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                textStyle = TextStyle(fontSize = 20.sp),
                colors = outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    isMenuExpanded = false
                    onClose()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
            ) {
                Text(
                    text = changeAccept,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun CustomDropdownMenuForMiddleName(
    buttonText: String,
    changeLabel: String,
    changeAccept: String,
    isOpen: Boolean,
    onClick: () -> Unit,
    onClose: () -> Unit
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }

    if (isMenuExpanded != isOpen) {
        isMenuExpanded = isOpen
    }

    LaunchedEffect(isOpen) {
        isMenuExpanded = if (isOpen) {
            true
        } else {
            delay(100)
            false
        }
    }

    Button(
        onClick = {
            if (isMenuExpanded) {
                isMenuExpanded = false
                onClose()
            } else {
                isMenuExpanded = true
                onClick()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        contentPadding = PaddingValues(0.dp),
        elevation = if (isMenuExpanded) ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 16.dp) else ButtonDefaults.buttonElevation(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .wrapContentHeight(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowUpward,
                    contentDescription = "Expand Menu",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            rotationZ = if (isMenuExpanded) 180f else 0f
                        }
                )
            }

            Spacer(Modifier.weight(1f))

            Text(
                text = buttonText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            )

            Spacer(Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDownward,
                    contentDescription = "Collapse Menu",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            rotationZ = if (isMenuExpanded) 0f else 180f
                        }
                )
            }
        }
    }

    if (isMenuExpanded) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(8.dp))
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = changeLabel, fontSize = 22.sp, color = MaterialTheme.colorScheme.onSurface) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                textStyle = TextStyle(fontSize = 20.sp),
                colors = outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    isMenuExpanded = false
                    onClose()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
            ) {
                Text(
                    text = changeAccept,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun NotificationSwitch() {
    val context = LocalContext.current
    val preferenceHelper = SharedPreferenceHelper(context)
    val (isNotificationsEnabled, setNotificationsEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "notifications_enabled")) }

    Box(modifier = Modifier.padding(16.dp)) {
        Column {
            NotificationToggle("Показывать уведомления", isNotificationsEnabled, setNotificationsEnabled, "notifications_enabled")
            if (isNotificationsEnabled) {
                // Чат
                val (isChatSwitchEnabled, setChatSwitchEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "chat_enabled")) }
                val (isNewMessageEnabled, setNewMessageEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "new_message_switch")) }
                val (isMentionEnabled, setMentionEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "mention_switch")) }
                val (isReplyEnabled, setReplyEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "reply_switch")) }

                NotificationToggle("Чат", isChatSwitchEnabled, setChatSwitchEnabled, "chat_enabled")

                LaunchedEffect(isNewMessageEnabled, isMentionEnabled, isReplyEnabled) {
                    if (!isNewMessageEnabled && !isMentionEnabled && !isReplyEnabled) {
                        setChatSwitchEnabled(false)
                        preferenceHelper.saveSwitchState(context, "chat_enabled", false)
                    }
                }

                if (isChatSwitchEnabled) {
                    NotificationSettingWithInfoAndSwitch(
                        "Новое сообщение",
                        "Уведомление о новом сообщении от другого пользователя в чате.",
                        isNewMessageEnabled,
                        "new_message_switch",
                        setNewMessageEnabled
                    )
                    NotificationSettingWithInfoAndSwitch(
                        "Упоминание",
                        "Уведомление, если вас упомянули в чате.",
                        isMentionEnabled,
                        "mention_switch",
                        setMentionEnabled
                    )
                    NotificationSettingWithInfoAndSwitch(
                        "Ответ на ваше сообщение",
                        "Уведомление о том, что кто-то ответил на ваше сообщение в чате.",
                        isReplyEnabled,
                        "reply_switch",
                        setReplyEnabled
                    )
                }

                // Занятия
                val (isLessonsEnabled, setLessonsEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "lessons_enabled")) }
                val (isLessonCancellationEnabled, setLessonCancellationEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "lesson_cancellation_switch")) }
                val (isLessonNotificationEnabled, setLessonNotificationEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "lesson_notification_switch")) }

                NotificationToggle("Занятия", isLessonsEnabled, setLessonsEnabled, "lessons_enabled")

                LaunchedEffect(isLessonCancellationEnabled, isLessonNotificationEnabled) {
                    if (!isLessonCancellationEnabled && !isLessonNotificationEnabled) {
                        setLessonsEnabled(false)
                        preferenceHelper.saveSwitchState(context, "lessons_enabled", false)
                    }
                }

                if (isLessonsEnabled) {
                    NotificationSettingWithInfoAndSwitch(
                        "Отмена занятия",
                        "Вам прийдёт уведомление о том, что ваш инструктор(ученик) отменил занятие.",
                        isLessonCancellationEnabled,
                        "lesson_cancellation_switch",
                        setLessonCancellationEnabled
                    )
                    NotificationSettingWithInfoAndSwitch(
                        "Уведомление о занятии",
                        "За 3 часа, Вам прийдёт уведомление о Вашем занятии, чтобы вы точно не пропустили его.",
                        isLessonNotificationEnabled,
                        "lesson_notification_switch",
                        setLessonNotificationEnabled
                    )
                }

                // Билеты
                val (isTicketsEnabled, setTicketsEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "tickets_enabled")) }
                val (isTicketChangesEnabled, setTicketChangesEnabled) = remember { mutableStateOf(preferenceHelper.getSwitchState(context, "ticket_changes_switch")) }

                NotificationToggle("Билеты", isTicketsEnabled, setTicketsEnabled, "tickets_enabled")

                LaunchedEffect(isTicketChangesEnabled) {
                    if (!isTicketChangesEnabled && isTicketsEnabled) {
                        setTicketsEnabled(false)
                        preferenceHelper.saveSwitchState(context, "tickets_enabled", false)
                    }
                }

                if (isTicketsEnabled) {
                    NotificationSettingWithInfoAndSwitch(
                        "Сообщать об изменениях в билетах",
                        "Вам будут приходить уведомления, если билеты ПДД, будут изменяться",
                        isTicketChangesEnabled,
                        "ticket_changes_switch",
                        setTicketChangesEnabled
                    )
                }
            }
        }
    }
}

@Composable
fun NotificationToggle(title: String, isChecked: Boolean, setChecked: (Boolean) -> Unit, key: String) {
    val context = LocalContext.current
    val preferenceHelper = SharedPreferenceHelper(context)
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = {
                setChecked(it)
                preferenceHelper.saveSwitchState(context, key, it)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )
        )
    }
}

@Composable
fun NotificationSettingWithInfoAndSwitch(title: String, info: String, isChecked: Boolean, key: String, setChecked: (Boolean) -> Unit) {
    val context = LocalContext.current
    val preferenceHelper = SharedPreferenceHelper(context)
    var isInfoVisible by remember { mutableStateOf(false) }
    val animatableAlpha = remember { Animatable(1f) }

    LaunchedEffect(isInfoVisible) {
        if (isInfoVisible) {
            delay(3000)
            animatableAlpha.animateTo(0f, animationSpec = tween(durationMillis = 1000))
            delay(1000)
            isInfoVisible = false
            animatableAlpha.snapTo(1f)
        }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 48.dp, top = 4.dp, end = 16.dp)
        ) {
            ClickableText(
                text = AnnotatedString(title),
                onClick = { isInfoVisible = true },
                style = LocalTextStyle.current.copy(
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = isChecked,
                onCheckedChange = {
                    setChecked(it)
                    preferenceHelper.saveSwitchState(context, key, it)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                )
            )
        }
        if (isInfoVisible) {
            Text(
                text = info,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 48.dp, top = 2.dp)
                    .alpha(animatableAlpha.value),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = animatableAlpha.value)
            )
        }
    }
}

@Composable
fun ThemeChangeSwitch(
    context: Context,
    buttonText: String,
    onSwitchChanged: (Boolean) -> Unit,
    key: String = "theme_state"
) {
    val preferenceHelper = SharedPreferenceHelper(context)
    var isNightMode by remember {
        mutableStateOf(
            preferenceHelper.getSwitchState(context, key)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                isNightMode = !isNightMode
                preferenceHelper.saveSwitchState(context, key, isNightMode)
                onSwitchChanged(isNightMode)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buttonText,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 22.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )

            Switch(
                checked = isNightMode,
                onCheckedChange = { newState ->
                    isNightMode = newState
                    preferenceHelper.saveSwitchState(context, key, newState)
                    onSwitchChanged(newState)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                ),
                thumbContent = {
                    Icon(
                        imageVector = if (isNightMode) Icons.Default.ModeNight else Icons.Default.WbSunny,
                        contentDescription = if (isNightMode) "Moon Icon" else "Sun Icon",
                        tint = if (isNightMode) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    }
}

@Composable
fun CircleWithLink(imagePainter: Painter, url: String) {
    val openUrlLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {}

    Box(
        modifier = Modifier
            .padding(start = 20.dp)
            .size(60.dp)
            .clip(CircleShape)
            .clickable {
                openUrlLauncher.launch(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
            .background(MaterialTheme.colorScheme.surface)
    ) {
        ImageComponent(painter = imagePainter)
    }
}

@Composable
fun ImageComponent(painter: Painter) {
    Box(modifier = Modifier.size(60.dp)) {
        Image(
            painter = painter,
            contentDescription = "Custom Image"
        )
    }
}

@Composable
fun BottomSheet(onDismiss: () -> Unit, viewModel: MapViewModel = viewModel()) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val selectedContent by viewModel.selectedContent.collectAsState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        modifier = Modifier.fillMaxHeight(0.8f),
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            if (selectedContent != null) {
                DisplayContent(content = selectedContent!!)
            } else {
                Text("No content available", color = MaterialTheme.colorScheme.onSurface)
            }
        }
    )
}

@Composable
fun DisplayContent(content: PlacemarkContent) {
    val context = LocalContext.current

    Column(
        Modifier.fillMaxHeight()
    ) {
        Text(
            text = "Автошкола 76",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.padding(horizontal = 5.dp))

        LazyColumn {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            RoundedCornerShape(16.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Общий номер телефона",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .padding(bottom = 8.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = "+7 (4852) 90-07-79",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                                .clickable {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:+74852900779")
                                    }
                                    context.startActivity(intent)
                                },
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
                Text(
                    text = content.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                )
                Text(
                    text = content.description,
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
            }
            item {
                ImageCarousel(imageUrls = content.imageUrls)

                Spacer(modifier = Modifier.height(15.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Divider(
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .padding(start = 5.dp)
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.onSurface,
                                RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ПАНОРАМА",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                    Divider(
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .padding(end = 5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ImageCarousel(imageUrls: List<String>) {

    val pagerState = rememberPagerState()
    var lastUserInteractionTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            lastUserInteractionTime = System.currentTimeMillis()
        }
    }

    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(3000)
            if (System.currentTimeMillis() - lastUserInteractionTime >= 6000) {
                val nextPage = (pagerState.currentPage + 1) % imageUrls.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    HorizontalPager(
        count = imageUrls.size,
        state = pagerState,
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) { page ->
        Box(
            modifier = Modifier
                .graphicsLayer {
                    val scale = lerp(
                        start = 0.85f,
                        stop = 1.00f,
                        fraction = 1f - abs(pagerState.currentPageOffset)
                    )
                    scaleX = scale
                    scaleY = scale
                }
                .shadow(4.dp, RoundedCornerShape(10.dp))
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(4.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrls[page]),
                contentDescription = "Image for carousel",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SwipeableTextCards(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = 3,
        state = pagerState,
        modifier = modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> TextPage("Автошкола была основана в 2015 году. Первое название - ООО \"ЛИДЕР АРТ\". Позднее организация утвердила свое торговое наименование \"Автошкола 76\" в связи с чем было принято решение взять идентичное юридическое название. Открытие второго филиала состоялось в 2018 и уже к 2020 году было создано 5 отдельных филиалов в каждом районе города, что позволяет обучать учеников Центра, Брагино, Заволги и Московского проспекта.\n")
            1 -> TextPage("Для удобства обучения в организации имеется не один, а сразу 2 собственных автодрома. Не секрет, что с 12 августа 2014 года с проблемой учебного автодрома столкнулась каждая автошкола. Одно из требований ГИБДД - размер учебной площадки должен составлять не менее 0,24 га. Мы построили свой собственный учебный автодром в Заволжском районе, который успешно прошел аттестацию в ГИБДД и соответствует всем новым требованиям. Второй автодром был приобретен чуть позже и располагается в Дзержинском районе. Когда мы заработали репутацию одной из лучших автошкол г.Ярославля, некоторые школы начали нам подражать и обманывать людей, представляясь Автошколой 76. Сегодня автошкола в ТЦ \"Фараон\" продолжает дезинформировать обучающихся пользуясь схожим названием сайта.\n")
            2 -> BenefitsPage()
        }
    }
}

@Composable
fun TextPage(text: String) {
    var isVisible by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    LaunchedEffect(key1 = true) {
        isVisible = true
        delay(1000)
        listState.animateScrollToItem(listState.layoutInfo.totalItemsCount - 1)
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 3000)) + expandVertically(animationSpec = tween(durationMillis = 3000))
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = text,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun BenefitsPage() {
    val benefits = listOf(
        "Нам подражают!",
        "Автошкола во всех районах города",
        "Два собственных автодрома: дзержинский и заволжский район",
        "Занятия по вождению идут по 120 минут",
        "Есть возможность обучаться на автомат",
        "Группа по теории утро, вечер, выходного дня, online",
        "Запись к инструктору на время и дни недели, когда удобно ученику",
        "Большой выбор машин, как отечественных так и иномарок",
        "Широкий график работы у инструкторов, включая выходные",
        "Сдача государственных экзаменов на нашей машине и площадке",
        "Опытные инструкторы",
        "Все машины брендированные, в хорошем техническом состоянии, оснащены видео и аудио фиксацией",
        "Есть несколько вариантов рассрочки на обучение",
        "Скидки студентам и военнослужащим",
        "Есть лицензия на обучение",
        "Фиксированная цена договора",
        "Запись на вождение через приложение, решение билетов ПДД через личный кабинет",
        "Работаем с 2015 года"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
    ) {
        Text(
            text = "Преимущества автошколы",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.onSurface
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(benefits.size) { index ->
                BenefitItem(text = benefits[index])
            }
        }
    }
}

@Composable
fun BenefitItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Check",
            modifier = Modifier.size(32.dp),
            tint = Color.Green
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}



// НАСТРОЙКА НАВИГАЦИИ

// USER + AUTHENTICATED
@Composable
fun CustomBottomBar(
    navController: NavController
) {
    val currentRoute = navController.currentDestination?.route

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBarItem(
                icon = Icons.Default.Home,
                label = "Билеты",
                isSelected = currentRoute == HOME_SCREEN,
                onClick = {
                    navController.navigate(HOME_SCREEN)
                }
            )
            BottomBarItem(
                icon = ImageVector.vectorResource(id = R.drawable.teacher_svgrepo_com),
                label = "Занятия",
                isSelected = currentRoute == ACCOUNT_SCREEN,
                onClick = {
                    navController.navigate(ACCOUNT_SCREEN)
                }
            )
            BottomBarItem(
                icon = Icons.Default.Chat,
                label = "Чат",
                isSelected = currentRoute == CHAT_SCREEN,
                onClick = {
                    navController.navigate(CHAT_SCREEN)
                }
            )
            BottomBarItem(
                icon = Icons.Default.Notifications,
                label = "Уведомления",
                isSelected = currentRoute == NOTIFICATION_SCREEN,
                onClick = {
                    navController.navigate(NOTIFICATION_SCREEN)
                }
            )
        }
    }
}

// INSTRUCTOR
@Composable
fun InstructorBottomBar(
    navController: NavController
) {
    val currentRoute = navController.currentDestination?.route

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBarItem(
                icon = ImageVector.vectorResource(id = R.drawable.teacher_svgrepo_com),
                label = "Занятия",
                isSelected = currentRoute == NavigationDestination.MainScreenInstructorDestination.destination,
                onClick = { navController.navigate(MAIN_SCREEN_INSTRUCTOR_SCREEN) }
            )
            BottomBarItem(
                icon = ImageVector.vectorResource(id = R.drawable.calendar_lines_pen_svgrepo_com),
                label = "Расписание",
                isSelected = currentRoute == NavigationDestination.CalendarInstructorDestination.destination,
                onClick = { navController.navigate(CALENDAR_INSTRUCTOR_DESTINATION_SCREEN) }
            )
            BottomBarItem(
                icon = Icons.Default.Search,
                label = "Списки",
                isSelected = currentRoute == NavigationDestination.ProfilesStudentsInstructorDestination.destination,
                onClick = { navController.navigate(PROFILES_STUDENTS_INSTRUCTOR_SCREEN) }
            )
            BottomBarItem(
                icon = Icons.Default.Chat,
                label = "Чат",
                isSelected = currentRoute == NavigationDestination.MessagesAndCommunicationsInstructorDestination.destination,
                onClick = { navController.navigate(MESSAGES_AND_COMMUNICATION_INSTRUCTOR_SCREEN) }
            )
            BottomBarItem(
                icon = Icons.Default.DirectionsCar,
                label = "Транспорт",
                isSelected = currentRoute == NavigationDestination.InfromationCarScreenInstructorDestination.destination,
                onClick = { navController.navigate(INFORMATION_ABOUT_CAR_SCREEN) }
            )
        }
    }
}

//MANAGER
@Composable
fun ManagerBottomBar(
    navController: NavController
) {
    val currentRoute = navController.currentDestination?.route

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            BottomBarItem(
                icon = Icons.Default.Home,
                label = "Домой",
                isSelected = currentRoute == NavigationDestination.HomeScreenDestination.destination,
                onClick = { navController.navigate(HOME_SCREEN) }
            )
            BottomBarItem(
                icon = Icons.Default.AccountCircle,
                label = "Аккаунт",
                isSelected = currentRoute == NavigationDestination.AccountScreenDestination.destination,
                onClick = { navController.navigate(ACCOUNT_SCREEN) }
            )
            BottomBarItem(
                icon = Icons.Default.Search,
                label = "Поиск",
                isSelected = currentRoute == NavigationDestination.SearchScreenDestination.destination,
                onClick = { navController.navigate(CHAT_SCREEN) }
            )
            BottomBarItem(
                icon = Icons.Default.Notifications,
                label = "Уведомления",
                isSelected = currentRoute == NavigationDestination.NotificationScreenDestination.destination,
                onClick = { navController.navigate(NOTIFICATION_SCREEN) }
            )
        }
    }
}


@Composable
fun BottomBarItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val selectedColor = SelectedBlue
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    color = if (isSelected) selectedColor else MaterialTheme.colorScheme.onSurface,
                    radius = 50.dp
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) selectedColor else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = label,
                color = if (isSelected) selectedColor else MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}




//Неавторизованный пользователь
@Composable
fun UnauthorizedScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Доступ запрещен",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}



//Подписка для authenticated роли
@Composable
fun SubscriptionRequiredScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
            .pointerInput(Unit) {
                detectTapGestures {

                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Купите подписку, чтобы открыть данный экран",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { navController.navigate(NavigationDestination.ShoppingCartScreenDestination.destination) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Купить подписку")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate(NavigationDestination.HomeScreenDestination.destination) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Решать билеты")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate(NavigationDestination.SettingsScreenDestination.destination) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Перейти в Настройки")
            }
        }
    }
}


//Instructor Screen
@Composable
fun RussianLicensePlate(carInformationViewModel: CarInformationViewModel = viewModel()) {
    var letter1 by remember { mutableStateOf("") }
    var numbers by remember { mutableStateOf("") }
    var letter2 by remember { mutableStateOf("") }
    var regionPart by remember { mutableStateOf("") }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequesterNumbers = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequesterRegion = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .padding(4.dp)
            .border(2.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = letter1,
            onValueChange = {
                val input = it.uppercase()
                if (input.length <= 1) {
                    letter1 = input
                }
                if (input.length == 1) {
                    focusRequesterNumbers.requestFocus()
                    carInformationViewModel.letter1.value = input
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 48.sp, letterSpacing = 4.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .weight(0.19f)
                .padding(7.dp)
                .focusRequester(focusRequester1),
            decorationBox = { innerTextField ->
                if (letter1.isEmpty()) {
                    Text("A", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), fontSize = 48.sp)
                }
                innerTextField()
            }
        )

        BasicTextField(
            value = numbers,
            onValueChange = {
                val input = it.uppercase()
                if (input.length <= 3) {
                    numbers = input
                }
                if (input.isEmpty()) {
                    focusRequester1.requestFocus()
                } else if (input.length == 3) {
                    focusRequester2.requestFocus()
                    carInformationViewModel.numbers.value = input
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 48.sp, letterSpacing = 4.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(0.32f)
                .padding(8.dp)
                .focusRequester(focusRequesterNumbers),
            decorationBox = { innerTextField ->
                if (numbers.isEmpty()) {
                    Text("777", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), fontSize = 48.sp)
                }
                innerTextField()
            }
        )

        BasicTextField(
            value = letter2,
            onValueChange = {
                val input = it.uppercase()
                if (input.length <= 2) {
                    letter2 = input
                }
                if (input.isEmpty()) {
                    focusRequesterNumbers.requestFocus()
                } else if (input.length == 2) {
                    focusRequesterRegion.requestFocus()
                    carInformationViewModel.letter2.value = input
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 48.sp, letterSpacing = 4.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .weight(0.32f)
                .padding(5.dp)
                .focusRequester(focusRequester2),
            decorationBox = { innerTextField ->
                if (letter2.isEmpty()) {
                    Text("AA", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), fontSize = 48.sp)
                }
                innerTextField()
            }
        )

        Box(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.onSurface)
        )

        BasicTextField(
            value = regionPart,
            onValueChange = {
                val input = it.uppercase()
                if (input.length <= 3) {
                    regionPart = input
                    carInformationViewModel.regionPart.value = input
                }
                if (input.isEmpty()) {
                    focusRequester2.requestFocus()
                }
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 48.sp,
                letterSpacing = 4.sp,
                textAlign = TextAlign.Center
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .weight(0.33f)
                .padding(8.dp)
                .focusRequester(focusRequesterRegion),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.Center) {
                    if (regionPart.isEmpty()) {
                        Text("777", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), fontSize = 48.sp)
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun CarBrandInput(viewModel: CarInformationViewModel = viewModel()) {
    val text by viewModel.carBrand.observeAsState("")
    val uiState by viewModel.uiState.observeAsState(UIState())

    OutlinedTextField(
        value = text,
        onValueChange = { viewModel.carBrand.value = it },
        label = { Text("Введите марку транспорта") },
        singleLine = true,
        isError = uiState.brandError,
        textStyle = TextStyle(fontSize = 24.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    )
    if (uiState.brandError) {
        Text("Вы не ввели марку транспорта", color = MaterialTheme.colorScheme.error, fontSize = 24.sp)
    }
}

@Composable
fun CarModelInput(viewModel: CarInformationViewModel = viewModel()) {
    val text by viewModel.carModel.observeAsState("")
    val uiState by viewModel.uiState.observeAsState(UIState())

    OutlinedTextField(
        value = text,
        onValueChange = { viewModel.carModel.value = it },
        label = { Text("Введите модель транспорта") },
        singleLine = true,
        isError = uiState.modelError,
        textStyle = TextStyle(fontSize = 24.sp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    )
    if (uiState.modelError) {
        Text("Вы не ввели модель транспорта", color = MaterialTheme.colorScheme.error, fontSize = 24.sp)
    }
}

@Composable
fun ConfirmationButton(viewModel: CarInformationViewModel, scope: CoroutineScope, imageUris: List<Uri>) {
    val isButtonEnabled by viewModel.isButtonEnabled.observeAsState(false)
    val showDialog by viewModel.showDialog.observeAsState(false)
    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.toggleDialog(false)
            },
            title = {
                Text(
                    text = "Данные о машине",
                    style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
            },
            text = {
                Column(modifier = Modifier.padding(8.dp)) {
                    val carData = buildString {
                        append("Марка: ${viewModel.carBrand.value}\n")
                        append("Модель: ${viewModel.carModel.value}\n")
                        append("Номер: ${viewModel.letter1.value}${viewModel.numbers.value}${viewModel.letter2.value} ${viewModel.regionPart.value}")
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(7.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = carData,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 23.sp)
                        )
                    }
                    Text(
                        text = "Убедитесь, что все данные, которые вы ввели, верны!",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.toggleDialog(false)
                        scope.launch {
                            viewModel.collectDataAndLog(imageUris, context)
                            viewModel.updateCarInformation()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(Transparent),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Сохранить данные")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.toggleDialog(false)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.error,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(Transparent),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Transparent,
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Отмена")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }

    Button(
        onClick = {
            scope.launch {
                viewModel.toggleDialog(true)
            }
        },
        enabled = isButtonEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isButtonEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    ) {
        Text("Подтвердить", fontSize = 18.sp)
    }
}

@Composable
fun ImagePicker() {
    var imageUris by remember { mutableStateOf(listOf<Uri>()) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val uploadStatus by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetMultipleContents()) { uris: List<Uri> ->
        imageUris = uris.take(3)
    }

    Box(
        modifier = Modifier
            .border(
                2.dp,
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            imageUris.forEachIndexed { index, uri ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Выбранное изображение $index",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                selectedImageUri = uri
                                showDialog = true
                            }
                            .padding(end = 8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Удалить изображение",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                imageUris = imageUris
                                    .toMutableList()
                                    .also { it.removeAt(index) }
                            }
                            .padding(4.dp)
                    )
                }
            }

            if (imageUris.size < 3) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_screpka),
                    contentDescription = "Добавить изображение",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { launcher.launch("image/*") }
                )
            }
        }
    }

    if (showDialog && selectedImageUri != null) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        onClick = { showDialog = false },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = "Просмотр изображения",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        contentScale = ContentScale.Fit
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Закрыть",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { showDialog = false },
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }

    uploadStatus?.let {
        Text(text = it, color = if (it.startsWith("Error")) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun AdCard(
    title: String,
    subtitle: String,
    price: String,
    oldPrice: String,
    description: String,
    imageUrl: String,
    onCardClick: () -> Unit = {}
) {
    var isPressed by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        if (isPressed) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface,
        label = ""
    )
    val borderColor = if (isSystemInDarkTheme()) DarkBorderColor else Transparent

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .graphicsLayer {
                if (isPressed) {
                    scaleX = 0.98f
                    scaleY = 0.98f
                } else {
                    scaleX = 1f
                    scaleY = 1f
                }
            }
            .border(BorderStroke(2.dp, borderColor), shape = RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectTapAndDragGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                        onCardClick()
                    },
                    onDrag = { change, _ ->
                        change.consume()
                    }
                )
            }
            .indication(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val painter = rememberAsyncImagePainter(imageUrl)
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = price,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = oldPrice,
                        fontSize = 18.sp,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

suspend fun PointerInputScope.detectTapAndDragGestures(
    onPress: suspend PressGestureScope.(Offset) -> Unit,
    onDrag: (PointerInputChange, Offset) -> Unit
) {
    coroutineScope {
        launch {
            detectTapGestures(onPress = onPress)
        }
        launch {
            detectDragGestures(onDrag = onDrag)
        }
    }
}


@Composable
fun ExpandableBox(
    shortText: String,
    fullText: String
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (!expanded) Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surfaceVariant
                    ),
                    startY = 0.5f * 300,
                    endY = 300f
                ) else Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
            .clickable(
                onClick = { expanded = !expanded },
                indication = rememberRipple(
                    bounded = true,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                ),
                interactionSource = remember { MutableInteractionSource() }
            )
            .animateContentSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (expanded) fullText else shortText,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ExpandableBoxForCatB() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .animateContentSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Обучение категории B Механика",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Автошкола предлагает профессиональное обучение для получения водительских прав категории B на механической трансмиссии. Обучение возможно в рассрочку без % и переплат, что позволяет вам получить необходимые навыки вождения без финансовых затруднений.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))

            Divider(color = MaterialTheme.colorScheme.onSurfaceVariant, thickness = 1.dp)

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Включено в обучение:",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "- Теоретические занятия",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "- 54 часа практического вождения",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "- Топливо для учебных автомобилей",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "- Экзамены (внутренние и государственные)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = MaterialTheme.colorScheme.onSurfaceVariant, thickness = 1.dp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Дополнительные затраты включают шоферскую комиссию и госпошлину за получение водительского удостоверения.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


//For Chat Instructor

@Composable
fun ChatListItem(
    chatMessage: ChatMessage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(chatMessage.avatar),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = chatMessage.username,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = chatMessage.message,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = chatMessage.timestamp,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}




//For List_Teah Instructor

@Composable
fun UserListItem(user: UserInfoCar, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(user.avatarUrl),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Оставшиеся часы: ${user.remainingHours}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun UserDetailDialog(user: UserInfoCar, onDismiss: () -> Unit, onNavigateToChat: () -> Unit) {
    AlertDialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.background,
            shadowElevation = 8.dp,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.avatarUrl),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Оставшиеся часы: ${user.remainingHours}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Телефон: ${user.phoneNumber}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onNavigateToChat() }) {
                    Text(text = "Перейти в чат")
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = DarkRedColor
                    ),
                    border = BorderStroke(1.dp, DarkRedColor)
                ) {
                    Text(text = "Закрыть", color = DarkRedColor)
                }
            }
        }
    }
}


//For Calendar Instructor
@Composable
fun PeopleList(dailyAppointments: List<DailyAppointments>, navController: NavController) {
    LazyColumn {
        items(dailyAppointments) { daily ->
            Text(
                text = "Дата: ${daily.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
            )
            daily.appointments.forEach { appointment ->
                var showDetails by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDetails = true }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(appointment.imageUrl),
                        contentDescription = "User Image",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.LightGray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = appointment.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Время: ${appointment.dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                if (showDetails) {
                    PersonDetailsDialog(
                        appointment = appointment,
                        navController = navController,
                        onClose = { showDetails = false }
                    )
                }
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DateRangePicker(
    startDate: LocalDate,
    endDate: LocalDate,
    onDateSelected: (LocalDate, LocalDate) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DatePickerButton(
            label = "Начало: $startDate",
            date = startDate,
            onDateChange = { newStartDate ->
                if (newStartDate <= endDate) {
                    onDateSelected(newStartDate, endDate)
                } else {
                    onDateSelected(newStartDate, newStartDate)
                }
            }
        )
        DatePickerButton(
            label = "Конец списка: $endDate",
            date = endDate,
            onDateChange = { newEndDate ->
                if (newEndDate >= startDate) {
                    onDateSelected(startDate, newEndDate)
                } else {
                    onDateSelected(startDate, startDate)
                }
            }
        )
    }
}

@Composable
fun DatePickerButton(label: String, date: LocalDate, onDateChange: (LocalDate) -> Unit) {
    val context = LocalContext.current
    Button(onClick = {
        showDatePicker(context, date) { onDateChange(it) }
    }) {
        Text(label)
    }
}

fun showDatePicker(context: Context, date: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    val listener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        onDateSelected(LocalDate.of(year, monthOfYear + 1, dayOfMonth))
    }
    val datePickerDialog = DatePickerDialog(
        context,
        listener,
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
    )
    datePickerDialog.datePicker.minDate = System.currentTimeMillis()
    val maxDateCalendar = Calendar.getInstance().apply {
        add(Calendar.MONTH, 3)
    }
    datePickerDialog.datePicker.maxDate = maxDateCalendar.timeInMillis
    datePickerDialog.show()
}

@Composable
fun PersonDetailsDialog(appointment: Appointment, navController: NavController, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        confirmButton = {
            Button(onClick = { navController.navigate("view_chat_screen/${appointment.id}") }) {
                Text("Перейти в чат")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onClose,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = DarkRedColor,
                    containerColor = Transparent
                ),
                border = BorderStroke(1.dp, DarkRedColor)
            ) {
                Text("Закрыть")
            }
        },
        title = {
            Text(
                text = "Подробная информация",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(appointment.imageUrl),
                    contentDescription = "User Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
                Text(
                    text = "Имя: ${appointment.name}",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Телефон: ${appointment.phone}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Email: ${appointment.email}",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    )
}



// For instructor classe's today
@Composable
fun AppointmentItem(appointment: Appointment, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(appointment.imageUrl),
            contentDescription = "User Image",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = appointment.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Время: ${appointment.dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    Divider(color = Color.Gray, thickness = 0.5.dp)
}




//User Screen's  Classe's Screen

@Composable
fun DateSelectionButton(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.time = java.util.Date.from(selectedDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant())

    Button(onClick = {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected(LocalDate.of(year, month + 1, dayOfMonth))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = System.currentTimeMillis()
            datePicker.maxDate = System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 90 // 3 months
        }.show()
    }) {
        Text("Выберите дату: ${selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yy"))}")
    }
}

@Composable
fun DateColumn(
    date: LocalDate,
    slots: List<DatePickerClass>,
    onSlotSelected: (DatePickerClass, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier
            .padding(8.dp)
            .width(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = getColorForDay(date),
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.format(DateTimeFormatter.ofPattern("dd.MM.yy")),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = date.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale("ru")),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            slots.forEach { slot ->
                TimeSlotRow(
                    slot = slot,
                    onCheckedChange = { isSelected ->
                        onSlotSelected(slot, isSelected)
                    }
                )
            }
        }
    }
}

@Composable
fun TimeSlotRow(
    slot: DatePickerClass,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(4.dp)
    ) {
        Checkbox(
            checked = slot.isSelected,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = "${slot.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${slot.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ConfirmationDialog(
    selectedSlots: List<DatePickerClass>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Подтвердите ваш выбор:") },
        text = {
            LazyColumn {
                items(selectedSlots) { slot ->
                    Column {
                        Text(text = "${slot.date.format(DateTimeFormatter.ofPattern("dd MMMM"))}:\nВремя: ${slot.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${slot.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Подтвердить выбор")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = DarkRedColor,
                    containerColor = Transparent
                ),
                border = BorderStroke(1.dp, DarkRedColor)
            ) {
                Text("Изменить выбор")
            }
        }
    )
}

@Composable
fun ConfirmedAppointmentsList(confirmedAppointments: Map<LocalDate, List<DatePickerClass>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        confirmedAppointments.forEach { (date, slots) ->
            Text(
                text = "Дата: ${date.format(DateTimeFormatter.ofPattern("dd MMMM"))}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            slots.forEach { slot ->
                Text(
                    text = "Время: ${slot.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))} - ${slot.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

fun generateDates(startDate: LocalDate): List<LocalDate> {
    return (0..14).map { startDate.plusDays(it.toLong()) }
}

fun createTimeSlots(dates: List<LocalDate>): List<DatePickerClass> {
    return dates.flatMap { date ->
        listOf(
            DatePickerClass(LocalTime.of(8, 0), LocalTime.of(9, 30), date),
            DatePickerClass(LocalTime.of(9, 30), LocalTime.of(11, 0), date),
            DatePickerClass(LocalTime.of(11, 0), LocalTime.of(12, 30), date),
            DatePickerClass(LocalTime.of(13, 0), LocalTime.of(14, 30), date),
            DatePickerClass(LocalTime.of(14, 30), LocalTime.of(16, 0), date),
            DatePickerClass(LocalTime.of(16, 0), LocalTime.of(17, 30), date),
            DatePickerClass(LocalTime.of(17, 30), LocalTime.of(19, 0), date),
            DatePickerClass(LocalTime.of(19, 0), LocalTime.of(20, 30), date)
        )
    }
}

fun getColorForDay(date: LocalDate): Color {
    return when (date.dayOfWeek) {
        java.time.DayOfWeek.SATURDAY, java.time.DayOfWeek.SUNDAY -> Color(0xFFFFCDD2)
        else -> Color(0xFFB2DFDB)
    }
}












