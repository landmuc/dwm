package com.landmuc.dwm.di

import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.authentication.data.repository.AuthenticationRepositoryImpl
import com.landmuc.dwm.authentication.domain.remote.AuthenticationRepository
import com.landmuc.dwm.authentication.domain.use_case.ConfirmPassword
import com.landmuc.dwm.authentication.domain.use_case.ValidateEmail
import com.landmuc.dwm.authentication.domain.use_case.ValidatePassword
import com.landmuc.dwm.authentication.presentation.sign_in.SignInViewModel
import com.landmuc.dwm.authentication.presentation.sign_up.SignUpScreenModel
import com.landmuc.dwm.authentication.presentation.sign_up.SignUpViewModel
import com.landmuc.dwm.task.data.repository.TaskDataRepositoryImpl
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import com.landmuc.dwm.task.presentation.TaskViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val supabaseClientModule = module {
    single { SupabaseClient }
}

val authRepModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}

val taskRepModule = module {
    single<TaskDataRepository> { TaskDataRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { ValidateEmail() }
    single { ValidatePassword() }
    single { ConfirmPassword() }
}

val authViewModelModule = module {
    factory { SignInViewModel(get())}
    factory { SignUpViewModel(get(), get(), get(), get()) }

}

val taskViewModelModule = module {
    factoryOf( ::TaskViewModel )
}


val sharedModule = module {
    includes(
        supabaseClientModule,
        authRepModule,
        taskRepModule,
        useCaseModule,
        authViewModelModule,
        taskViewModelModule,
    )
}