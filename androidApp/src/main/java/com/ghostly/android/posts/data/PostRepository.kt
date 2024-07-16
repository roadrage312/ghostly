package com.ghostly.android.posts.data

import com.ghostly.android.network.models.Result
import com.ghostly.android.posts.models.PostsResponse

interface PostRepository {
    suspend fun getPosts(page: Int, pageSize: Int): Result<PostsResponse>
}

class PostRepositoryImpl(
    private val remoteDataSource: PostDataSource,
    private val localDataSource: PostDataSource
) : PostRepository {
    override suspend fun getPosts(page: Int, pageSize: Int): Result<PostsResponse> {
        // Implement logic to switch between remote and local data sources
        return remoteDataSource.getPosts(page, pageSize)
    }
}