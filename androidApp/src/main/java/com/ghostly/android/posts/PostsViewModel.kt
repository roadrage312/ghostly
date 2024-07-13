package com.ghostly.android.posts

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ghostly.android.data.parsePostsJson
import com.ghostly.android.posts.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class PostsViewModel : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _selectedFilter = MutableStateFlow<Filter>(Filter.All)
    val selectedFilter: StateFlow<Filter> = _selectedFilter

    val filters = listOf(Filter.All, Filter.Drafts, Filter.Published, Filter.Scheduled)

    fun onFilterChange(filter: Filter) {
        _selectedFilter.value = filter
    }

    suspend fun getPosts(context: Context) = withContext(Dispatchers.IO) {
        val postsResponse = parsePostsJson(context, "posts.json")
        _posts.value = postsResponse.posts
    }
}