package com.ghostly.android.network

import org.koin.dsl.module

val networkModule = module {
    single { client }
    single<ApiService> { ApiServiceImpl(get(), get(), get(), get()) }
    single<TokenProvider> { TokenProviderImpl(get()) }
}