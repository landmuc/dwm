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
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.landmuc.dwm.core.theme.AccentViolet
import dwm.composeapp.generated.resources.Res
import dwm.composeapp.generated.resources.confirm_password
import dwm.composeapp.generated.resources.email
import dwm.composeapp.generated.resources.password
import dwm.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

object SignUpScreen: Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(Res.string.sign_up))},
                    navigationIcon = {
                        IconButton(
                            onClick = { navigator?.pop() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Res.string.arrow_back"
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
                value = "value",
                onValueChange = {},
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
                value = "value",
                onValueChange = {},
                placeholder = { Text(stringResource(Res.string.password)) },
                leadingIcon =  { Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(Res.string.password)
                )},
                visualTransformation = PasswordVisualTransformation() // masks the password input
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
                value = "value",
                onValueChange = {},
                placeholder = { Text(stringResource(Res.string.confirm_password)) },
                leadingIcon =  { Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(Res.string.confirm_password)
                )},
                visualTransformation = PasswordVisualTransformation() // masks the password input
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
            )
            Button(
                onClick = {},
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
}

//@OptIn(ExperimentalResourceApi::class)
//@Composable
//fun SignUnScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // text field to enter email for sign up
//        OutlinedTextField(
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp, 4.dp)
//                .border(
//                    BorderStroke(width = 2.dp, color = AccentViolet),
//                    shape = RoundedCornerShape(50)
//                ),
//            value = "value",
//            onValueChange = {},
//            placeholder = { Text(stringResource(Res.string.email)) },
//            leadingIcon =  { Icon(
//                imageVector = Icons.Default.Email,
//                contentDescription = stringResource(Res.string.email)
//            )}
//        )
//        // text field to enter password for sign up
//        OutlinedTextField(
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp, 4.dp)
//                .border(
//                    BorderStroke(width = 2.dp, color = AccentViolet),
//                    shape = RoundedCornerShape(50)
//                ),
//            value = "value",
//            onValueChange = {},
//            placeholder = { Text(stringResource(Res.string.password)) },
//            leadingIcon =  { Icon(
//                imageVector = Icons.Default.Lock,
//                contentDescription = stringResource(Res.string.password)
//            )},
//            visualTransformation = PasswordVisualTransformation() // masks the password input
//        )
//        // text field to confirm password above
//        OutlinedTextField(
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp, 4.dp)
//                .border(
//                    BorderStroke(width = 2.dp, color = AccentViolet),
//                    shape = RoundedCornerShape(50)
//                ),
//            value = "value",
//            onValueChange = {},
//            placeholder = { Text(stringResource(Res.string.confirm_password)) },
//            leadingIcon =  { Icon(
//                imageVector = Icons.Default.Lock,
//                contentDescription = stringResource(Res.string.confirm_password)
//            )},
//            visualTransformation = PasswordVisualTransformation() // masks the password input
//        )
//        Spacer(modifier = Modifier
//            .fillMaxWidth()
//            .padding(12.dp)
//        )
//        Button(
//            onClick = {},
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp, 0.dp),
//            shape = RoundedCornerShape(50)
//        ) {
//            Text(
//                text = stringResource(Res.string.sign_up),
//                fontSize = 16.sp,
//                modifier = Modifier.padding(vertical = 6.dp)
//            )
//        }
//    }
//}

//@Preview()
//@Composable
//fun SignUpScreenPreview() {
//    DWMTheme {
//        SignUpScreen()
//    }
//}
