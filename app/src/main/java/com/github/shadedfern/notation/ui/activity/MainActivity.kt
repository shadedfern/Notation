package com.github.shadedfern.notation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.shadedfern.notation.ui.components.NavigationBar
import com.github.shadedfern.notation.ui.theme.NotationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotationTheme {
                NavigationBar()
            }
        }
    }
}

