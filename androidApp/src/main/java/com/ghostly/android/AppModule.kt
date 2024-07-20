package com.ghostly.android

import com.ghostly.android.login.loginModule
import com.ghostly.android.posts.postsModule
import com.ghostly.android.settings.SettingsViewModel
import com.ghostly.androidModules
import com.ghostly.ghostCommonModules
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

private val appModule = module {
    viewModelOf(::SettingsViewModel)
}

val allModules = listOf(appModule, loginModule, postsModule) + ghostCommonModules + androidModules