package com.ghostly.mappers

import com.ghostly.database.entities.AuthorEntity
import com.ghostly.database.entities.PostEntity
import com.ghostly.database.entities.PostWithAuthorsAndTags
import com.ghostly.database.entities.TagEntity
import com.ghostly.posts.models.Author
import com.ghostly.posts.models.Post
import com.ghostly.posts.models.Tag

internal fun PostWithAuthorsAndTags.toPost(): Post {
    return Post(
        id = post.id,
        slug = post.slug,
        title = post.title,
        content = post.html,
        featureImage = post.featureImage,
        status = post.status,
        createdAt = post.createdAt,
        updatedAt = post.updatedAt,
        publishedAt = post.publishedAt,
        url = post.url,
        visibility = post.visibility,
        excerpt = post.excerpt,
        authors = authors.map { it.toAuthor() },
        tags = tags.map { it.toTag() }
    )
}

internal fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        id,
        slug,
        title,
        content,
        featureImage,
        status,
        visibility,
        createdAt,
        updatedAt,
        publishedAt,
        url,
        excerpt
    )
}

internal fun TagEntity.toTag(): Tag {
    return Tag(
        id = id,
        name = name,
        slug = slug
    )
}

internal fun AuthorEntity.toAuthor(): Author {
    return Author(
        id = id,
        name = name,
        profileImage = profileImage,
        slug = slug
    )
}