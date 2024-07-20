package com.ghostly.android.posts

import com.ghostly.android.utils.getTimeAgo
import com.ghostly.posts.models.Post

fun Post.getTimeFromStatus(): String {
    val time = updatedAt ?: publishedAt ?: createdAt
    return getTimeAgo(time)
}