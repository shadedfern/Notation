package com.github.shadedfern.notation.ui.components.converter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.shadedfern.notation.R

@Composable
fun ChoiceDialog(
    onClick: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    selectedRadix: Int,
    title: String
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Column {
                listOf(2, 8, 10, 16).forEach { choice ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = choice.toString())

                        RadioButton(
                            selected = (selectedRadix == choice),
                            onClick = { onClick(choice) }
                        )
                    }
                }
            }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            FilledTonalButton(onClick = onDismissRequest) { Text(stringResource(id = R.string.confirm_dialogue)) }
        }
    )
}