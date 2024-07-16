package com.ghostly.android.posts

import com.ghostly.android.posts.data.GetPostsUseCase
import com.ghostly.android.posts.data.GetPostsUseCaseImpl
import com.ghostly.android.posts.data.PostDataSource
import com.ghostly.android.posts.data.PostRepository
import com.ghostly.android.posts.data.PostRepositoryImpl
import com.ghostly.android.posts.data.RemotePostDataSource
import org.koin.dsl.module

val postsModule = module {
    single<PostDataSource> { RemotePostDataSource(get()) }
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
    single<GetPostsUseCase> { GetPostsUseCaseImpl(get()) }
}