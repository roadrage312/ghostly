package com.ghostly.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ghostly.android.posts.EmptyPostView
import com.ghostly.android.posts.PostItem
import com.ghostly.android.posts.PostsViewModel
import org.koin.androidx.compose.koinViewModel

private val filters = listOf("All", "Drafts", "Published", "Scheduled")

@Composable
fun PostsScreen(
    mainNavController: NavHostController,
    postsViewModel: PostsViewModel = koinViewModel()
) {
    val posts by postsViewModel.posts.collectAsState()
    val selectedFilter by postsViewModel.selectedFilter.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FilterChipGroup(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            items = filters,
            selectedFilter
        ) {
            postsViewModel.onFilterChange(filters[it])
        }

        val filteredPosts = if (selectedFilter == "All")
            posts
        else
            posts.filter { it.status == selectedFilter }

        if (filteredPosts.isEmpty()) {
            EmptyPostView(postsViewModel)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(filteredPosts) { post ->
                    PostItem(mainNavController, post)
                }
            }
        }
    }
}

@Composable
fun FilterChipGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedFilter: String,
    onSelectedChanged: (Int) -> Unit = {}
) {
    LazyRow(
        modifier = modifier,
        userScrollEnabled = true,
        horizontalArrangement = Arrangement.Start
    ) {
        items(items.size) { index: Int ->
            FilterChip(
                modifier = Modifier.padding(end = 6.dp),
                selected = selectedFilter == items[index],
                onClick = {
                    onSelectedChanged(index)
                },
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = true,
                    borderColor = Color.White,
                ),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.Transparent,
                    selectedContainerColor = MaterialTheme.colorScheme.tertiary
                ),
                label = { Text(items[index]) },
                leadingIcon = {},
                shape = MaterialTheme.shapes.medium
            )

        }
    }
}

@Preview
@Composable
fun PostsScreenPreview() {
    PostsScreen(rememberNavController())
}