package com.landmuc.dwm.di

import com.landmuc.dwm.task.data.remote.SupabaseClient
import com.landmuc.dwm.task.data.repository.AuthenticationRepositoryImpl
import com.landmuc.dwm.task.domain.remote.AuthenticationRepository
import com.landmuc.dwm.task.domain.use_case.ConfirmPassword
import com.landmuc.dwm.task.domain.use_case.ValidateEmail
import com.landmuc.dwm.task.domain.use_case.ValidatePassword
import com.landmuc.dwm.task.presentation.sign_in.SignInScreenModel
import com.landmuc.dwm.task.presentation.sign_up.SignUpScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val authRepModule = module {
    single { SupabaseClient }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { ValidateEmail() }
    single { ValidatePassword() }
    single { ConfirmPassword() }
}

val screenModelModule = module {
    factory { SignInScreenModel(get()) }
    factory { SignUpScreenModel(get(), get(), get(), get()) }
    //factoryOf( ::SignUpScreenModel)
}


val sharedModule = module {
    includes(authRepModule, useCaseModule, screenModelModule)
}