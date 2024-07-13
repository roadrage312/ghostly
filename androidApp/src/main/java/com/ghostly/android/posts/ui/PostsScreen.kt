package com.ghostly.android.posts.ui

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ghostly.android.posts.models.Filter
import com.ghostly.android.posts.PostsViewModel
import com.ghostly.android.posts.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostsScreen(
    postsViewModel: PostsViewModel = koinViewModel(),
    onPostClick: (Post) -> Unit
) {
    val context = LocalContext.current
    val posts by postsViewModel.posts.collectAsState()
    val selectedFilter by postsViewModel.selectedFilter.collectAsState()

    //Todo Needs to change with API integration
    LaunchedEffect(Unit) {
        launch(Dispatchers.IO) {
            postsViewModel.getPosts(context)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FilterChipGroup(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            items = postsViewModel.filters,
            selectedFilter
        ) {
            postsViewModel.onFilterChange(postsViewModel.filters[it])
        }

        val filteredPosts = if (selectedFilter == Filter.All)
            posts
        else
            posts.filter { it.status == selectedFilter.key }

        if (filteredPosts.isEmpty()) {
            EmptyPostView(postsViewModel)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(filteredPosts) { post ->
                    PostItem(post, selectedFilter == Filter.All, onPostClick)
                }
            }
        }
    }
}

@Composable
fun FilterChipGroup(
    modifier: Modifier = Modifier,
    items: List<Filter>,
    selectedFilter: Filter,
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
                label = {
                    Text(
                        text = items[index].filterName,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight(300))
                    )
                },
                leadingIcon = {},
                shape = MaterialTheme.shapes.medium
            )
        }
    }
}

@Preview
@Composable
fun PostsScreenPreview() {
    PostsScreen {}
}