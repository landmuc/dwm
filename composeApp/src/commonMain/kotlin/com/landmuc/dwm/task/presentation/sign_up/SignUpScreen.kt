package com.landmuc.dwm.task.presentation.sign_up

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.landmuc.dwm.core.theme.AccentViolet
import dwm.composeapp.generated.resources.Res
import dwm.composeapp.generated.resources.arrow_back
import dwm.composeapp.generated.resources.confirm_password
import dwm.composeapp.generated.resources.email
import dwm.composeapp.generated.resources.password
import dwm.composeapp.generated.resources.sign_up
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.mp.KoinPlatform

object SignUpScreen: Screen {
    @Composable
    override fun Content() {
        val signUpScreenModel: SignUpScreenModel = KoinPlatform.getKoin().get()
        val screenModel = rememberScreenModel { signUpScreenModel }

        SignUpScreenRoot(screenModel = screenModel)
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignUpScreenRoot(
    screenModel: SignUpScreenModel
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val controller = LocalSoftwareKeyboardController.current

    val navigator = LocalNavigator.current

    val email by screenModel.emailInput.collectAsState()
    val password by screenModel.passwordInput.collectAsState()
    val passwordConfirm by screenModel.passwordConfirmInput.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.sign_up))},
                navigationIcon = {
                    IconButton(
                        onClick = { navigator?.pop() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(Res.string.arrow_back)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // text field to enter email for sign up
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = AccentViolet),
                        shape = RoundedCornerShape(50)
                    ),
                value = email,
                onValueChange = screenModel::updateEmailInput,
                placeholder = { Text(stringResource(Res.string.email)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = stringResource(Res.string.email)
                    )
                }
            )
            // text field to enter password for sign up
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = AccentViolet),
                        shape = RoundedCornerShape(50)
                    ),
                value = password,
                onValueChange = screenModel::updatePasswordInput,
                placeholder = { Text(stringResource(Res.string.password)) },
                leadingIcon =  { Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(Res.string.password)
                )},
                //visualTransformation = PasswordVisualTransformation() // masks the password input
            )
            // text field to confirm password above
            OutlinedTextField(
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 4.dp)
                    .border(
                        BorderStroke(width = 2.dp, color = AccentViolet),
                        shape = RoundedCornerShape(50)
                    ),
                value = passwordConfirm,
                onValueChange = screenModel::updatePasswordConfirmInput,
                placeholder = { Text(stringResource(Res.string.confirm_password)) },
                leadingIcon =  { Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(Res.string.confirm_password)
                )},
                //visualTransformation = PasswordVisualTransformation() // masks the password input
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
            )
            Button(
                onClick = {
                    controller?.hide()
                    screenModel.signUp { signUpResult ->
                        if (signUpResult) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "$signUpResult -> You successfully signed up! Log in on the first screen!",
                                    duration = SnackbarDuration.Long
                                )
                            }
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "$signUpResult -> Something went wrong. You could not sign up.",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = stringResource(Res.string.sign_up),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
    }
}