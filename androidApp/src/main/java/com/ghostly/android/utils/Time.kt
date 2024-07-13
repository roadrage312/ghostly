package com.ghostly.android.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

fun getTimeAgo(dateTimeString: String): String {
    val dateTime = LocalDateTime.ofInstant(Instant.parse(dateTimeString), ZoneId.systemDefault())
    val now = LocalDateTime.now()
    val duration = ChronoUnit.SECONDS.between(dateTime, now)

    val minutes = TimeUnit.SECONDS.toMinutes(duration)
    val hours = TimeUnit.SECONDS.toHours(duration)
    val days = TimeUnit.SECONDS.toDays(duration)

    return when {
        minutes < 1 -> "Just now"
        minutes < 60 -> "$minutes mins ago"
        hours < 2 -> "An hr ago"
        hours < 24 -> "$hours hrs ago"
        days < 2 -> "Yesterday"
        days < 7 -> "$days days ago"

        else -> dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
    }
}
