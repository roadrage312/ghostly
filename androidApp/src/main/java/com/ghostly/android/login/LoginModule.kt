package com.ghostly.android.login

import com.ghostly.datastore.LoginDetailsStore
import com.ghostly.datastore.LoginDetailsStoreImpl
import com.ghostly.login.data.GetSiteDetailsUseCase
import com.ghostly.login.data.GetSiteDetailsUseCaseImpl
import com.ghostly.login.data.SiteRepository
import com.ghostly.login.data.SiteRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    single<GetSiteDetailsUseCase> { GetSiteDetailsUseCaseImpl(get()) }
    single<SiteRepository> { SiteRepositoryImpl(get()) }
    single<LoginDetailsStore> { LoginDetailsStoreImpl(get()) }

    viewModelOf(::LoginViewModel)
}