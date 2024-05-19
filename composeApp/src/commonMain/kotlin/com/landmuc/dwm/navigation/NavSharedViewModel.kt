package com.landmuc.dwm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.landmuc.dwm.core.koin.koinViewModel
import org.koin.compose.LocalKoinScope

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    // Access Koin scope using LocalKoinScope
    val koinScope = LocalKoinScope.current
    return viewModel(parentEntry) {
        koinScope.get<T>()
    }
}