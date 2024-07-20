package com.ghostly.android.posts

import android.os.Bundle
import androidx.navigation.NavType
import com.ghostly.android.posts.ui.decodeFromNavigation
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        bundle.getString(key)?.let<String, T> {
            json.decodeFromString(decodeFromNavigation(it))
        }

    override fun parseValue(value: String): T = json.decodeFromString(decodeFromNavigation(value))

    override fun serializeAsValue(value: T): String =
        encodeForNavigation(json.encodeToString(value))

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, encodeForNavigation(json.encodeToString(value)))
    }
}

fun encodeForNavigation(input: String): String {
    return URLEncoder.encode(input, StandardCharsets.UTF_8.toString())
}