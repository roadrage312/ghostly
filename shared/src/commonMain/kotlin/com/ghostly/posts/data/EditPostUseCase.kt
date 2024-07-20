package com.ghostly.posts.data

import com.ghostly.network.models.Result
import com.ghostly.posts.models.Post
import com.ghostly.posts.models.UpdateRequestWrapper

interface EditPostUseCase {
    suspend operator fun invoke(
        postId: String,
        request: UpdateRequestWrapper,
    ): Result<Post>
}

internal class EditPostUseCaseImpl(
    private val postRepository: PostRepository,
) : EditPostUseCase {
    override suspend fun invoke(
        postId: String,
        request: UpdateRequestWrapper,
    ): Result<Post> = postRepository.publishPost(postId, request)
}