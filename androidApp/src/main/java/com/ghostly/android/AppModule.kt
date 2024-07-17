package com.ghostly.android

import com.ghostly.android.datastore.DataStoreRepository
import com.ghostly.android.datastore.DataStoreRepositoryImpl
import com.ghostly.android.login.LoginViewModel
import com.ghostly.android.login.models.LoginDetailsStore
import com.ghostly.android.login.models.LoginDetailsStoreImpl
import com.ghostly.android.posts.PostsViewModel
import com.ghostly.android.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::PostsViewModel)
    viewModelOf(::SettingsViewModel)


    single<LoginDetailsStore> { LoginDetailsStoreImpl(get()) }
    single<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}