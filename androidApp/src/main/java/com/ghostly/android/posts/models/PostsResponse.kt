package com.ghostly.android.posts.models

import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val posts: List<Post>
)