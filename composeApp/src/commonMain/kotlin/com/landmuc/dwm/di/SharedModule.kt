package com.landmuc.dwm.di

import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.authentication.data.repository.AuthenticationRepositoryImpl
import com.landmuc.dwm.authentication.domain.remote.AuthenticationRepository
import com.landmuc.dwm.authentication.domain.use_case.ConfirmPassword
import com.landmuc.dwm.authentication.domain.use_case.ValidateEmail
import com.landmuc.dwm.authentication.domain.use_case.ValidatePassword
import com.landmuc.dwm.authentication.presentation.sign_in.SignInScreenModel
import com.landmuc.dwm.authentication.presentation.sign_in.SignInViewModel
import com.landmuc.dwm.authentication.presentation.sign_up.SignUpScreenModel
import com.landmuc.dwm.task.data.repository.TaskDataRepositoryImpl
import com.landmuc.dwm.task.domain.remote.TaskDataRepository
import com.landmuc.dwm.task.presentation.SecondTestScreenModel
import com.landmuc.dwm.task.presentation.TaskScreenModel
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

val authScreenModelModule = module {
    factory { SignInScreenModel(get()) }
    //factory { SignUpScreenModel(get(), get(), get(), get()) }
    factoryOf( ::SignUpScreenModel )
}

val taskScreenModelModule = module {
    factoryOf( ::TaskScreenModel )
}

val authViewModelModule = module {
    factoryOf( ::SignInViewModel )
}

val taskViewModelModule = module {
    factoryOf( ::TaskViewModel )
}

val secondTestScreenModelModule = module {
    //factoryOf( ::SecondTestScreenModel )
    factory { SecondTestScreenModel(get(), get()) }
}


val sharedModule = module {
    includes(
        supabaseClientModule,
        authRepModule,
        taskRepModule,
        useCaseModule,
        authScreenModelModule,
        taskScreenModelModule,
        authViewModelModule,
        taskViewModelModule,
        secondTestScreenModelModule
    )
}