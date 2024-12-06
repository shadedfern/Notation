package com.github.shadedfern.notation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    buttonText: String,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Text(buttonText, fontWeight = FontWeight.Bold)
    }
}