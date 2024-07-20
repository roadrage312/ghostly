package com.ghostly.android.posts

import androidx.paging.ExperimentalPagingApi
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val postsModule = module {
    viewModelOf(::PostDetailViewModel)
    viewModelOf(::PostsViewModel)
}