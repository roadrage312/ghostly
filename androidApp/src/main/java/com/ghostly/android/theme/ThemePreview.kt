package com.ghostly.android.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextStylesPreview() {
    Column {
        Text(
            text = "DisplayLarge",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "DisplayMedium",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "DisplaySmall",
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = "HeadlineLarge",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "HeadlineMedium",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "HeadlineSmall",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "TitleLarge",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "TitleMedium",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "TitleSmall",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "BodyLarge",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "BodyMedium",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "BodySmall",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "LabelLarge",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "LabelMedium",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "LabelSmall",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextStyles() {
    AppTheme(darkTheme = false) {
        TextStylesPreview()
    }
}