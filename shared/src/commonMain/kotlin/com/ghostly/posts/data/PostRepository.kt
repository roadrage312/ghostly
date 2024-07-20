package com.ghostly.posts.data

import androidx.paging.ExperimentalPagingApi
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.ghostly.database.dao.PostDao
import com.ghostly.database.entities.PostWithAuthorsAndTags
import com.ghostly.mappers.toPost
import com.ghostly.network.ApiService
import com.ghostly.network.models.Result
import com.ghostly.posts.models.Post
import com.ghostly.posts.models.PostsResponse
import com.ghostly.posts.models.UpdateRequestWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal interface PostRepository {
    suspend fun getOnePost(): Result<PostsResponse>

    fun getPosts(
        pageSize: Int,
        prefetchDistance: Int = pageSize,
    ): Flow<PagingData<PostWithAuthorsAndTags>>

    suspend fun publishPost(
        postId: String,
        requestWrapper: UpdateRequestWrapper,
    ): Result<Post>

    suspend fun getPostById(id: String): Flow<Post>
}

@OptIn(ExperimentalPagingApi::class)
internal class PostRepositoryImpl(
    private val apiService: ApiService,
    private val postDao: PostDao,
    private val postRemoteMediator: PostRemoteMediator,
    private val postDataSource: PostDataSource,
) : PostRepository {
    override suspend fun getOnePost(): Result<PostsResponse> {
        return apiService.getPosts(1, 1)
    }

    override suspend fun publishPost(
        postId: String,
        requestWrapper: UpdateRequestWrapper,
    ): Result<Post> {
        return when (val result = apiService.publishPost(postId, requestWrapper)) {
            is Result.Success -> {
                val posts = result.data?.posts?.takeIf { it.isNotEmpty() } ?: return Result.Error(
                    -1,
                    "Something went wrong"
                )
                postDataSource.updatePost(posts.first())
                Result.Success(posts.first())
            }

            is Result.Error -> Result.Error(result.errorCode, result.message)
        }
    }

    override fun getPosts(
        pageSize: Int,
        prefetchDistance: Int,
    ): Flow<PagingData<PostWithAuthorsAndTags>> {
        val pagingDataSource = { postDao.getAllPostsWithAuthorsAndTags() }

        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                initialLoadSize = pageSize,
                prefetchDistance = prefetchDistance
            ),
            pagingSourceFactory = pagingDataSource,
            remoteMediator = postRemoteMediator
        ).flow
    }

    override suspend fun getPostById(id: String): Flow<Post> {
        return postDao.getPostWithAuthorsAndTags(id).map {
            it.toPost()
        }
    }
}