package com.ghostly.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val slug: String,
    val title: String,
    val html: String,
    val featureImage: String?,
    val status: String,
    val visibility: String?,
    val createdAt: String,
    val updatedAt: String?,
    val publishedAt: String?,
    val url: String,
    val excerpt: String?,
)

@Serializable
@Entity(tableName = "authors")
data class AuthorEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val slug: String,
    val profileImage: String?,
)

@Serializable
@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val slug: String,
)

@Entity(primaryKeys = ["postId", "authorId"], tableName = "post_author_cross_ref")
data class PostAuthorCrossRef(
    val postId: String,
    val authorId: String,
)

@Entity(primaryKeys = ["postId", "tagId"], tableName = "post_tag_cross_ref")
data class PostTagCrossRef(
    val postId: String,
    val tagId: String,
)

@Serializable
data class PostWithAuthorsAndTags(
    @Embedded val post: PostEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(PostAuthorCrossRef::class, parentColumn = "postId", entityColumn = "authorId")
    )
    val authors: List<AuthorEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(PostTagCrossRef::class, parentColumn = "postId", entityColumn = "tagId")
    )
    val tags: List<TagEntity>
)