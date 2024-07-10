package com.ghostly.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ghostly.Greeting
import com.ghostly.android.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(
                darkTheme = false
            ) {
                GreetingView(Greeting().greet())
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        GreetingView(Greeting().greet())
    }
}
