package com.ghostly.android

import com.ghostly.android.datastore.CryptedDataStore
import com.ghostly.android.datastore.NewCryptedDataStoreImpl
import com.ghostly.android.login.LoginViewModel
import com.ghostly.android.login.models.LoginDetailsStore
import com.ghostly.android.login.models.LoginDetailsStoreImpl
import com.ghostly.android.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::PostsViewModel)

    single<CryptedDataStore> { NewCryptedDataStoreImpl() }
    single<LoginDetailsStore> { LoginDetailsStoreImpl(get(), get()) }
}