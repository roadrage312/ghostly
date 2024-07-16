package com.ghostly.android.posts.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ghostly.android.network.models.Result
import com.ghostly.android.posts.models.Post
import com.ghostly.android.posts.models.PostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface GetPostsUseCase {
    operator fun invoke(pageSize: Int, prefetchDistance: Int = pageSize): Flow<PagingData<Post>>

    suspend fun getOnePost(): Result<PostsResponse>
}

class GetPostsUseCaseImpl(private val postRepository: PostRepository) : GetPostsUseCase {
    override fun invoke(pageSize: Int, prefetchDistance: Int): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                initialLoadSize = pageSize,
                prefetchDistance = prefetchDistance
            ),
            pagingSourceFactory = { PostPagingSource(postRepository) }
        ).flow
    }

    override suspend fun getOnePost(): Result<PostsResponse> =
        withContext(Dispatchers.IO) {
            return@withContext postRepository.getPosts(1, 1)
        }
}