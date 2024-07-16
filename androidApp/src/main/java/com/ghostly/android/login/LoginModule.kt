package com.ghostly.android.login

import com.ghostly.android.login.data.GetSiteDetailsUseCase
import com.ghostly.android.login.data.GetSiteDetailsUseCaseImpl
import com.ghostly.android.login.data.SiteRepository
import com.ghostly.android.login.data.SiteRepositoryImpl
import org.koin.dsl.module

val loginModule = module {
    single<GetSiteDetailsUseCase> { GetSiteDetailsUseCaseImpl(get()) }
    single<SiteRepository> { SiteRepositoryImpl(get()) }
}