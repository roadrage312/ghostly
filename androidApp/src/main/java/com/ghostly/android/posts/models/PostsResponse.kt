package com.ghostly.android.posts.models

import com.ghostly.android.network.models.Meta
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val posts: List<Post>,
    val meta: Meta? = null,
)