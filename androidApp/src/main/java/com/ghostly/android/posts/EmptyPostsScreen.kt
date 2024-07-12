package com.ghostly.android.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostly.android.R
import com.ghostly.android.ui.components.PrimaryButton

@Composable
fun EmptyPostView(postsViewModel: PostsViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.no_posts),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(24.dp))
        PrimaryButton(
            onClick = { postsViewModel.onFilterChange(Filter.All) },
            shape = MaterialTheme.shapes.medium,
        ) {
            Text(
                text = stringResource(R.string.show_all_posts),
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp)
            )
        }
    }
}