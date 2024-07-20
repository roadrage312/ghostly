package com.ghostly.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghostly.database.dao.AuthorDao
import com.ghostly.database.dao.PostAuthorCrossRefDao
import com.ghostly.database.dao.PostDao
import com.ghostly.database.dao.PostTagCrossRefDao
import com.ghostly.database.dao.RemoteKeysDao
import com.ghostly.database.dao.TagDao
import com.ghostly.database.entities.AuthorEntity
import com.ghostly.database.entities.PostAuthorCrossRef
import com.ghostly.database.entities.PostEntity
import com.ghostly.database.entities.PostTagCrossRef
import com.ghostly.database.entities.RemoteKeys
import com.ghostly.database.entities.TagEntity

@Database(
    entities = [
        PostEntity::class,
        AuthorEntity::class,
        TagEntity::class,
        PostAuthorCrossRef::class,
        PostTagCrossRef::class,
        RemoteKeys::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class GhostlyDatabase : RoomDatabase() {
    abstract val postDao: PostDao
    abstract val authorDao: AuthorDao
    abstract val tagDao: TagDao
    abstract val postAuthorCrossRefDao: PostAuthorCrossRefDao
    abstract val postTagCrossRefDao: PostTagCrossRefDao
    abstract val remoteKeysDao: RemoteKeysDao
}
