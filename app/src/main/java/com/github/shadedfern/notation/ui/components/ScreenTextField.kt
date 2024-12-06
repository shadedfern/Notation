package com.github.shadedfern.notation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    result: String,
    error: Boolean = false,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        maxLines = 1,
        isError = error,
        supportingText = { Text(result) },
        textStyle = TextStyle(fontWeight = FontWeight.Bold),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}

