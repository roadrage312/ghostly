package com.ghostly.android.posts.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ghostly.android.network.models.Result
import com.ghostly.android.posts.models.Post

class PostPagingSource(private val postRepository: PostRepository) : PagingSource<Int, Post>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            when (val result = postRepository.getPosts(page, pageSize)) {
                is Result.Success -> {
                    LoadResult.Page(
                        data = result.data?.posts ?: emptyList(),
                        prevKey = result.data?.meta?.pagination?.prev,
                        nextKey = result.data?.meta?.pagination?.next
                    )
                }

                is Result.Error -> {
                    LoadResult.Error(Exception(result.message))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}