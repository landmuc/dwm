package com.landmuc.dwm.di

import com.landmuc.dwm.task.data.remote.SupabaseClient
import com.landmuc.dwm.task.data.repository.AuthenticationRepositoryImpl
import com.landmuc.dwm.task.domain.remote.AuthenticationRepository
import com.landmuc.dwm.task.presentation.sign_in.SignInScreenModel
import com.landmuc.dwm.task.presentation.sign_up.SignUpScreenModel
import org.koin.dsl.module


val authRepModule = module {
    single { SupabaseClient }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}

val screenModelModule = module {
    factory { SignInScreenModel(get()) }
    factory { SignUpScreenModel(get()) }
}


val sharedModule = module {
    includes(authRepModule, screenModelModule)
}