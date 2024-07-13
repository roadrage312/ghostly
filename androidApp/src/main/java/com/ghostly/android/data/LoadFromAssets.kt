package com.ghostly.android.data

import android.content.Context
import com.ghostly.android.posts.models.PostsResponse
import kotlinx.serialization.json.Json

//Temp file
//Todo remove this
fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

private val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

fun parsePostsJson(context: Context, fileName: String): PostsResponse {
    val jsonString = readJsonFromAssets(context, fileName)
    return json.decodeFromString(jsonString)
}
