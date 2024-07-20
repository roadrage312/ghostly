package com.ghostly.posts.data

import app.cash.paging.PagingData
import app.cash.paging.map
import com.ghostly.mappers.toPost
import com.ghostly.network.models.Result
import com.ghostly.posts.models.Post
import com.ghostly.posts.models.PostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface GetPostsUseCase {
    operator fun invoke(
        pageSize: Int,
        prefetchDistance: Int = pageSize,
    ): Flow<PagingData<Post>>

    suspend fun getOnePost(): Result<PostsResponse>

    suspend fun getPostById(id: String): Flow<Post>
}

internal class GetPostsUseCaseImpl(
    private val postRepository: PostRepository,
) : GetPostsUseCase {
    override fun invoke(
        pageSize: Int,
        prefetchDistance: Int,
    ): Flow<PagingData<Post>> {
        return postRepository.getPosts(pageSize, prefetchDistance).map { pagingData ->
            pagingData.map { postWithAuthorsAndTags ->
                postWithAuthorsAndTags.toPost()
            }
        }
    }

    override suspend fun getOnePost(): Result<PostsResponse> = withContext(Dispatchers.IO) {
        return@withContext postRepository.getOnePost()
    }

    override suspend fun getPostById(id: String): Flow<Post> {
        return postRepository.getPostById(id)
    }
}