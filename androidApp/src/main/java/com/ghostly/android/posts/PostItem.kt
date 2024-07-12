package com.ghostly.android.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ghostly.android.R
import com.ghostly.android.theme.accentRed
import com.ghostly.android.ui.components.verticalGradient
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PostItem(navController: NavHostController, post: Post, showStatus: Boolean) {
    val encodedAuthorName = encodeForNavigation(post.author.name)
    val encodedAuthorAvatarUrl = encodeForNavigation(post.author.avatarUrl)
    val encodedTime = encodeForNavigation(post.time)
    val encodedTitle = encodeForNavigation(post.title)
    val encodedContent = encodeForNavigation(post.content)
    val encodedPrimaryTag = encodeForNavigation(post.primaryTag)
    val encodedImageUrl = encodeForNavigation(post.imageUrl)

    val filter = remember(post.status) { statusKeyToFilter(post.status) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            shape = MaterialTheme.shapes.large,
            onClick = {
                //TODO Open detail screen
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(post.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                AssistChip(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .height(30.dp),
                    onClick = { /*TODO*/ },
                    label = {
                        Text(
                            text = post.primaryTag,
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    shape = MaterialTheme.shapes.medium.copy(CornerSize(6.dp)),
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = MaterialTheme.colorScheme.scrim,
                        containerColor = Color.White.copy(alpha = 0.6f),
                    ),
                    border = AssistChipDefaults.assistChipBorder(
                        enabled = true,
                        borderColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Filled.Sell,
                            contentDescription = stringResource(R.string.cd_tag),
                            tint = MaterialTheme.colorScheme.scrim
                        )
                    },
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .height(50.dp)
                        .verticalGradient(
                            0.0f to Color.Black.copy(0.0f),
                            0.5f to Color.Black.copy(0.3f),
                            1f to Color.Black.copy(0.4f),
                        )
                )
                if (showStatus) {
                    val statusColor = when (filter) {
                        Filter.Drafts -> accentRed
                        else -> MaterialTheme.colorScheme.onPrimary
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .padding(4.dp)
                            .height(24.dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .align(Alignment.CenterVertically),
                            text = filter.title,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight(
                                    600
                                )
                            ),
                            maxLines = 1,
                            color = statusColor
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(300)),
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(end = 2.dp),
        ) {
            AsyncImage(
                model = post.author.avatarUrl,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(CircleShape),
                contentDescription = stringResource(R.string.cd_author),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                text = post.author.name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight(600)),
                maxLines = 1
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = post.time,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
        }
    }
}

private fun statusKeyToFilter(status: String): Filter {
    return when (status) {
        Filter.Drafts.key -> Filter.Drafts
        Filter.Published.key -> Filter.Published
        Filter.Scheduled.key -> Filter.Scheduled
        else -> Filter.All
    }
}

private fun encodeForNavigation(input: String): String {
    return URLEncoder.encode(input, StandardCharsets.UTF_8.toString())
}