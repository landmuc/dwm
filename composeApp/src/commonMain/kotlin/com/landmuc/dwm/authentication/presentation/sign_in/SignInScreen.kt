package com.landmuc.dwm.authentication.presentation.sign_in

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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
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
import com.landmuc.dwm.authentication.presentation.sign_up.SignUpScreen
import com.landmuc.dwm.task.presentation.TaskScreen
import dwm.composeapp.generated.resources.Res
import dwm.composeapp.generated.resources.email
import dwm.composeapp.generated.resources.password
import dwm.composeapp.generated.resources.sign_in
import dwm.composeapp.generated.resources.sign_up_description
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.mp.KoinPlatform

object SignInScreen: Screen {
    @Composable
    override fun Content() {
        val signInScreenModel: SignInScreenModel = KoinPlatform.getKoin().get()
        val screenModel = rememberScreenModel { signInScreenModel }

        SignInScreenRoot(screenModel = screenModel)
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignInScreenRoot(
    screenModel: SignInScreenModel
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val controller = LocalSoftwareKeyboardController.current

    val navigator = LocalNavigator.current

    val email by screenModel.emailInput.collectAsState()
    val password by screenModel.passwordInput.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // text field to enter email for sign in
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
            // text field to enter password for sign in
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
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = stringResource(Res.string.password)
                    )
                },
                visualTransformation = PasswordVisualTransformation() // masks the password input
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
            Button(
                onClick = {
                    controller?.hide()
                    screenModel.signIn { signInResult ->
                        if (signInResult) {
                            navigator?.push(TaskScreen)
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Invalid email and/or password!",
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
                    text = stringResource(Res.string.sign_in),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            TextButton(
                onClick = { navigator?.push(SignUpScreen) },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(Res.string.sign_up_description),
                    fontSize = 16.sp
                )
            }
        }
    }

}

