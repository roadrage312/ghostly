package com.ghostly.android.posts

import com.ghostly.posts.models.Author
import com.ghostly.posts.models.Tag
import kotlin.reflect.typeOf

object PostsConstants {
    const val DEFAULT_IMAGE_URL = "https://placehold.co/600x400/webp?text=Image%20Missing"
    const val DEFAULT_PROFILE_IMAGE_URL = "https://i.ibb.co/Km9D083/user.png"
}

object NavigationMaps {
    val typeMap = mapOf(
        typeOf<List<Author>>() to serializableType<List<Author>>(),
        typeOf<List<Tag>>() to serializableType<List<Tag>>(),
        typeOf<Author>() to serializableType<Author>(),
        typeOf<Tag>() to serializableType<Tag>(),
    )
}