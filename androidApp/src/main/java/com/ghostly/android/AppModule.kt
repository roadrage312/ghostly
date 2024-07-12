package com.ghostly.android

import com.ghostly.android.login.LoginViewModel
import com.ghostly.android.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { PostsViewModel() }
}
