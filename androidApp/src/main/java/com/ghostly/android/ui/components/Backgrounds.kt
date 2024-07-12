package com.ghostly.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.verticalGradient(vararg colorStops: Pair<Float, Color>): Modifier {
    return this.background(
        brush =
        Brush.verticalGradient(
            colorStops = colorStops,
        ),
    )
}