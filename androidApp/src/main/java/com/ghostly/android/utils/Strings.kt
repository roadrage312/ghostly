package com.ghostly.android.utils

import android.util.Patterns

fun isValidEmail(target: CharSequence?): Boolean =
    target?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } == true