package com.ghostly.posts.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class Post(
    val id: String,
    val slug: String,
    @SerialName("created_at")
    val createdAt: String,
    val title: String,
    @SerialName("html")
    val content: String,
    @SerialName("feature_image")
    val featureImage: String? = null,
    val status: String, // "draft", "scheduled", "published"
    @SerialName("published_at")
    val publishedAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val url: String,
    val visibility: String?,
    val excerpt: String?,
    val authors: List<Author>,
    val tags: List<Tag>,
)

@Serializable
data class Author(
    val id: String,
    val name: String,
    @SerialName("profile_image")
    val profileImage: String? = "https://i.ibb.co/Km9D083/user.png",//default user image
    val slug: String,
)

@Serializable
data class Tag(
    val id: String,
    val name: String,
    val slug: String,
)
