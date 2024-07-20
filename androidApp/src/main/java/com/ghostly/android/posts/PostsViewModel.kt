package com.ghostly.android.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.filter
import com.ghostly.posts.data.GetPostsUseCase
import com.ghostly.posts.models.Filter
import com.ghostly.posts.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

class PostsViewModel(
    getPostsUseCase: GetPostsUseCase,
) : ViewModel() {

    private val _selectedFilter = MutableStateFlow<Filter>(Filter.All)
    val selectedFilter: StateFlow<Filter> = _selectedFilter

    private val allPosts: Flow<PagingData<Post>> = getPostsUseCase.invoke(30)
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