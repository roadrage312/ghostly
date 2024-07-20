package com.ghostly.posts.models

import com.ghostly.network.models.Meta
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val posts: List<Post>,
    val meta: Meta? = null,
)