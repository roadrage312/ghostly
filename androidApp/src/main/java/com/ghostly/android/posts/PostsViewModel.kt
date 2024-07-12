package com.ghostly.android.posts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PostsViewModel : ViewModel() {
    private val _posts = MutableStateFlow(
        listOf(
            Post(
                author = "Jishnu Hari",
                time = "2 hr ago",
                title = "5 tips to create a modern app UI Design",
                content = "Recently I was given the task to “Modernize” my current companies app UI on Android. The terms modern, young, cool, etc., are all quite vague. What makes a design modern or old?",
                imageUrl = "https://images.unsplash.com/photo-1719886260713-f6127e297181?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "News",
                status = "Published",
            ),
            Post(
                author = "Jenny Wilson",
                time = "2 hr ago",
                title = "Why Minimal UI Design is the Go-To Trend in 2022",
                content = "Minimalism is a design aesthetic that embodies the phrase “less is more.” With minimalist design, you push an idea by stripping it down to essential",
                imageUrl = "https://images.unsplash.com/photo-1720264715773-ec63cbb81e52?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Technology",
                status = "Drafts"
            )
        )
    )

    val posts: StateFlow<List<Post>> = _posts

    private val _selectedFilter = MutableStateFlow("All")
    val selectedFilter: StateFlow<String> = _selectedFilter

    fun onFilterChange(newFilter: String) {
        _selectedFilter.value = newFilter
    }
}