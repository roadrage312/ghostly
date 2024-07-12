package com.ghostly.android.posts

data class Post(
    val author: Author,
    val time: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val primaryTag: String,
    val status: String, // "Drafts", "Scheduled", "Published"
)

data class Author(
    val name: String,
    val avatarUrl: String,
)
