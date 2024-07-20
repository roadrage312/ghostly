package com.ghostly.android.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun getTimeAgo(dateTimeString: String): String {
    val dateTime = LocalDateTime.ofInstant(Instant.parse(dateTimeString), ZoneId.systemDefault())
    val now = LocalDateTime.now()
    val duration = ChronoUnit.SECONDS.between(dateTime, now)

    val minutes = duration / 60
    val hours = minutes / 60
    val days = hours / 24

    return when {
        minutes < 1 -> "Just now"
        minutes < 60 -> "$minutes mins ago"
        hours < 1 -> "$minutes mins ago"
        hours < 2 -> "An hr ago"
        hours < 24 -> "$hours hrs ago"
        days < 1 -> "$hours hrs ago"
        days == 1L && now.dayOfYear - dateTime.dayOfYear == 1 -> "Yesterday"
        days < 2 -> "1 day ago"
        days < 7 -> "$days days ago"
        else -> dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
    }
}

fun getCurrentTimeFormatted(): String {
    val currentTime = Instant.now()
    val formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC)
    // Format the current time without milliseconds
    val timeWithoutMillis = ZonedDateTime.ofInstant(currentTime, ZoneOffset.UTC).withNano(0)
    return formatter.format(timeWithoutMillis)
}