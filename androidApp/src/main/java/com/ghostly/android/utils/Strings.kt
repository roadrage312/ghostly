package com.ghostly.android.utils

import android.util.Patterns
import java.util.Locale
import java.util.regex.Pattern

private val GHOST_DOMAIN =
    Pattern.compile("^https?://((?!-)[A-Za-z0–9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}/ghost\$")

fun isValidEmail(target: CharSequence?): Boolean =
    target?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } == true

fun isValidGhostDomain(target: CharSequence?): Boolean =
    target?.let { GHOST_DOMAIN.matcher(it).matches() } == true

fun String.capitalize(): String =
    this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }