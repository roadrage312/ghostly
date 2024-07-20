package com.ghostly.posts.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateRequest(
    @SerialName("updated_at")
    val updatedAt: String,
    val status: String
)

@Serializable
data class UpdateRequestWrapper(
    val posts: List<UpdateRequest>
)