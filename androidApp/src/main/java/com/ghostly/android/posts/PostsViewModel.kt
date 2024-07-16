package com.ghostly.android.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.ghostly.android.posts.data.GetPostsUseCase
import com.ghostly.android.posts.models.Filter
import com.ghostly.android.posts.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

class PostsViewModel(
    getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _selectedFilter = MutableStateFlow<Filter>(Filter.All)
    val selectedFilter: StateFlow<Filter> = _selectedFilter

    private val allPosts: Flow<PagingData<Post>> = getPostsUseCase(20)
        .cachedIn(viewModelScope)

    val filteredPosts: Flow<PagingData<Post>> = allPosts
        .combine(selectedFilter) { pagingData, status ->
            if (status == Filter.All) {
                pagingData
            } else {
                pagingData.filter { post ->
                    post.status == status.key
                }
            }
        }
        .cachedIn(viewModelScope)

    val filters = listOf(Filter.All, Filter.Drafts, Filter.Published, Filter.Scheduled)

    fun onFilterChange(filter: Filter) {
        _selectedFilter.value = filter
    }
}