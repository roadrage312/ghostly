package com.ghostly.android.posts.models

import com.ghostly.android.posts.serializableType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data class Post(
    val authors: List<Author>,
    @SerialName("created_at")
    val time: String,
    val title: String,
    @SerialName("html")
    val content: String,
    @SerialName("feature_image")
    val imageUrl: String?,
    val tags: List<Tag>,
    val status: String, // "draft", "scheduled", "published"
) {
    companion object {
        val typeMap = mapOf(
            typeOf<List<Author>>() to serializableType<List<Author>>(),
            typeOf<List<Tag>>() to serializableType<List<Tag>>(),
            typeOf<Author>() to serializableType<Author>(),
            typeOf<Tag>() to serializableType<Tag>(),
        )
    }
}

@Serializable
data class Author(
    val name: String,
    @SerialName("profile_image")
    val avatarUrl: String = "https://i.ibb.co/Km9D083/user.png",//default user image
)

@Serializable
data class Tag(
    val name: String,
    val slug: String,
)
