package com.ghostly.android.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ghostly.android.R
import com.ghostly.android.posts.models.Post
import com.ghostly.android.posts.models.Tag
import com.ghostly.android.theme.tagColors
import com.ghostly.android.utils.getTimeAgo
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(navController: NavController, post: Post) {
    val filter = remember(post.status) { Filter.statusKeyToFilter(post.status) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.height(54.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.posts),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "  /  ",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            modifier = Modifier,
                            text = filter.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondaryContainer
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(bottom = 48.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Tags(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                tags = post.tags
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .padding(end = 2.dp),
            ) {
                if (post.authors.isEmpty().not()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(post.authors[0].avatarUrl)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier
                            .padding(2.dp)
                            .aspectRatio(1f)
                            .clip(CircleShape),
                        contentDescription = stringResource(R.string.cd_author),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                        text = post.authors[0].name,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight(100)),
                        maxLines = 1
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = getTimeAgo(post.time),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(thickness = 1.dp)
            if (post.imageUrl.isNullOrEmpty().not()) {
                Spacer(modifier = Modifier.height(16.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(post.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            val richTextState = rememberRichTextState()
            LaunchedEffect(Unit) {
                richTextState.setHtml(post.content)
            }
            RichText(
                state = richTextState,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(modifier: Modifier, tags: List<Tag>) {

    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxLines = 2,
        overflow = FlowRowOverflow.Visible,
    ) {
        tags.forEach { tag ->
            val tagColor = remember(tag.name) { tagColors.random() }
            AssistChip(
                modifier = Modifier
                    .height(24.dp),
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text = tag.name,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1
                    )
                },
                shape = MaterialTheme.shapes.large,
                colors = AssistChipDefaults.assistChipColors(
                    labelColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = tagColor,
                ),
                border = AssistChipDefaults.assistChipBorder(
                    enabled = true,
                    borderColor = Color.Transparent
                ),
                leadingIcon = {},
            )
        }
    }
}

fun decodeFromNavigation(input: String): String {
    return URLDecoder.decode(input, StandardCharsets.UTF_8.toString())
}