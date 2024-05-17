package com.landmuc.dwm.core.koin

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope

/**
 * Bind the viewmodel injected by koin to the lifecycle of the screen
 * just using koininject() would create a new instance of the viewmodel each time
 * the screen is recreated
 */
@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}