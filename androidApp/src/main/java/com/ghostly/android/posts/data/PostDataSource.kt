package com.ghostly.android.posts.data

import com.ghostly.android.network.ApiService
import com.ghostly.android.network.models.Result
import com.ghostly.android.posts.models.PostsResponse

interface PostDataSource {
    suspend fun getPosts(page: Int, pageSize: Int): Result<PostsResponse>
}

class LocalPostDataSource : PostDataSource {
    override suspend fun getPosts(page: Int, pageSize: Int): Result<PostsResponse> {
        // Implement local data source logic
        return Result.Success(PostsResponse(emptyList()))
    }
}

class RemotePostDataSource(private val apiService: ApiService) : PostDataSource {
    override suspend fun getPosts(page: Int, pageSize: Int): Result<PostsResponse> {
        return apiService.getPosts(page, pageSize)
    }
}