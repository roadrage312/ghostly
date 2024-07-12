package com.ghostly.android.posts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PostsViewModel : ViewModel() {
    private val _posts = MutableStateFlow(
        listOf(
            Post(
                author = "Alice Johnson",
                time = "3 hr ago",
                title = "The Future of Mobile App Development",
                content = "As technology advances, the future of mobile app development looks promising with AI integration and AR experiences.",
                imageUrl = "https://images.unsplash.com/photo-1719764339252-ee15cfc7736c?q=80&w=3159&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Technology",
                status = "Published"
            ),
            Post(
                author = "Bob Smith",
                time = "1 hr ago",
                title = "How to Optimize Your Website for SEO",
                content = "SEO is crucial for improving your website's visibility. Learn the latest strategies to optimize your site effectively.",
                imageUrl = "https://images.unsplash.com/photo-1517554558809-9b4971b38f39?q=80&w=2304&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Marketing",
                status = "Published"
            ),
            Post(
                author = "Catherine Lee",
                time = "4 hr ago",
                title = "Top 10 Design Principles for Creating User-Friendly Interfaces",
                content = "Creating user-friendly interfaces requires understanding key design principles. Here are the top 10 you should know.",
                imageUrl = "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Design",
                status = "Published"
            ),
            Post(
                author = "David Kim",
                time = "30 min ago",
                title = "Understanding the Basics of Machine Learning",
                content = "Machine learning is transforming industries. Learn the basics and how you can start using it in your projects.",
                imageUrl = "https://images.unsplash.com/photo-1561577916-30708e9dafc7?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Technology",
                status = "Drafts"
            ),
            Post(
                author = "Emily Davis",
                time = "6 hr ago",
                title = "Effective Social Media Strategies for 2024",
                content = "Stay ahead of the curve with these effective social media strategies designed for the year 2024.",
                imageUrl = "https://images.unsplash.com/photo-1542405413-7e07a2e3350e?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Marketing",
                status = "Published"
            ),
            Post(
                author = "Franklin Moore",
                time = "5 hr ago",
                title = "An Introduction to Quantum Computing",
                content = "Quantum computing is a revolutionary technology. This introduction will give you a foundational understanding.",
                imageUrl = "https://images.unsplash.com/photo-1720561023559-60b0a17098b6?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Science",
                status = "Drafts"
            ),
            Post(
                author = "Grace Thompson",
                time = "1 day ago",
                title = "Top Tips for Successful Remote Work",
                content = "Remote work is here to stay. These tips will help you stay productive and maintain a work-life balance.",
                imageUrl = "https://plus.unsplash.com/premium_photo-1683121714135-b3939f85b845?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Lifestyle",
                status = "Published"
            ),
            Post(
                author = "Henry Johnson",
                time = "2 days ago",
                title = "The Impact of Artificial Intelligence on Healthcare",
                content = "AI is revolutionizing healthcare. Explore the impact of AI on medical practices and patient care.",
                imageUrl = "https://images.unsplash.com/photo-1719588537050-f0da1fb57b6d?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Health",
                status = "Drafts"
            ),
            Post(
                author = "Isabella Martinez",
                time = "3 days ago",
                title = "Sustainable Living: How to Make Your Home Eco-Friendly",
                content = "Sustainable living starts at home. Here are some tips to make your living space more eco-friendly.",
                imageUrl = "https://images.unsplash.com/photo-1659999463470-8d01d61cf9e8?q=80&w=2160&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Lifestyle",
                status = "Drafts"
            ),
            Post(
                author = "Jack Lee",
                time = "4 days ago",
                title = "How Blockchain is Changing the Financial Industry",
                content = "Blockchain technology is disrupting the financial industry. Learn how it works and its potential applications.",
                imageUrl = "https://images.unsplash.com/photo-1504868584819-f8e8b4b6d7e3?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                primaryTag = "Finance",
                status = "Published"
            ),
        )
    )

    val posts: StateFlow<List<Post>> = _posts

    private val _selectedFilter = MutableStateFlow("All")
    val selectedFilter: StateFlow<String> = _selectedFilter

    fun onFilterChange(newFilter: String) {
        _selectedFilter.value = newFilter
    }
}