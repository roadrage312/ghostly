package com.ghostly.android.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ghostly.android.R
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PostItem(navController: NavHostController, post: Post) {
    val encodedAuthor = encodeForNavigation(post.author)
    val encodedTime = encodeForNavigation(post.time)
    val encodedTitle = encodeForNavigation(post.title)
    val encodedContent = encodeForNavigation(post.content)
    val encodedPrimaryTag = encodeForNavigation(post.primaryTag)
    val encodedImageUrl = encodeForNavigation(post.imageUrl)

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
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 2.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_round_person_24),
                contentDescription = "person",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                text = post.author,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = post.time,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

fun encodeForNavigation(input: String): String {
    return URLEncoder.encode(input, StandardCharsets.UTF_8.toString())
}