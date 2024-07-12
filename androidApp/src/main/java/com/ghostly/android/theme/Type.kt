package com.ghostly.android.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostly.android.R

@OptIn(ExperimentalTextApi::class)
private val displayLargeFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(950),
            FontVariation.width(30f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val displayMediumFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600),
            FontVariation.width(30f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val displaySmallFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(300),
            FontVariation.width(30f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val headlineLargeFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(700),
            FontVariation.width(25f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val headlineMediumFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600),
            FontVariation.width(25f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val headlineSmallFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500),
            FontVariation.width(25f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val titleLargeFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(700),
            FontVariation.width(20f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val titleMediumFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600),
            FontVariation.width(20f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val titleSmallFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500),
            FontVariation.width(20f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val bodyLargeFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400),
            FontVariation.width(20f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val bodyMediumFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(300),
            FontVariation.width(20f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val bodySmallFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(200),
            FontVariation.width(20f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val labelLargeFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400),
            FontVariation.width(30f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val labelMediumFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500),
            FontVariation.width(15f),
            FontVariation.slant(0f),
        )
    )
)

@OptIn(ExperimentalTextApi::class)
private val labelSmallFontFamily = FontFamily(
    Font(
        R.font.inter_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400),
            FontVariation.width(15f),
            FontVariation.slant(0f),
        )
    )
)

val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = displayLargeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = displayMediumFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = displaySmallFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = headlineLargeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = headlineMediumFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = headlineSmallFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = titleLargeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = titleMediumFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = titleSmallFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = bodyLargeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = displayLargeFontFamily,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = bodySmallFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = labelLargeFontFamily,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = labelMediumFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = labelSmallFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    )
)

val shapes = Shapes(
    small = RoundedCornerShape(2.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(8.dp)
)