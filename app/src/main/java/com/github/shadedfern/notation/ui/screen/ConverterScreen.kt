package com.github.shadedfern.notation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.shadedfern.notation.R
import com.github.shadedfern.notation.ui.components.converter.ConverterCard
import com.github.shadedfern.notation.ui.components.PrimaryButton
import com.github.shadedfern.notation.ui.components.TopBar
import com.github.shadedfern.notation.ui.components.TextField
import com.github.shadedfern.notation.ui.components.Title
import com.github.shadedfern.notation.ui.components.converter.ChoiceDialog

@Composable
fun ConverterScreen(modifier: Modifier = Modifier) {

    var userInput: String by rememberSaveable() { mutableStateOf("") }
    var isError: Boolean by rememberSaveable() { mutableStateOf(false) }
    var result: String by rememberSaveable() { mutableStateOf("") }

    var showInitialRadixChoice by remember { mutableStateOf(false) }
    var showTargetRadixChoice by remember { mutableStateOf(false) }
    var selectedInitialRadix by remember { mutableStateOf(2) }
    var selectedTargetRadix by remember { mutableStateOf(10) }

    Scaffold(
        topBar = {
            TopBar()
        },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = userInput,
                        onValueChange = {
                            userInput = it
                            isError = false
                            result = ""
                        },
                        result = result,
                        error = isError,
                        label = stringResource(id = R.string.label_convert)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        PrimaryButton(
                            onClick = {
                                try {
                                    result = Integer.parseInt(userInput, selectedInitialRadix).toString(selectedTargetRadix)
                                } catch (e: NumberFormatException) {
                                    isError = true
                                }
                            },
                            stringResource(id = R.string.convert_button)
                        )
                    }
                }
                Title(
                    titleText = stringResource(id = R.string.title_convert),
                    subtitleText = stringResource(id = R.string.subtitle_convert)
                )
                ConverterCard(
                    showInitialRadixChoice = showInitialRadixChoice,
                    showTargetRadixChoice = showTargetRadixChoice,
                    selectedInitialRadix = selectedInitialRadix,
                    selectedTargetRadix = selectedTargetRadix,
                    onInitialClick = { showInitialRadixChoice = true },
                    onTargetClick = { showTargetRadixChoice = true },
                    onSwapClick = {
                        val tempInitialRadix = selectedInitialRadix
                        selectedInitialRadix = selectedTargetRadix
                        selectedTargetRadix = tempInitialRadix
                    }
                )
            }
            if (showInitialRadixChoice) {
                ChoiceDialog(
                    onClick = { choice -> selectedInitialRadix = choice },
                    onDismissRequest = { showInitialRadixChoice = false },
                    selectedRadix = selectedInitialRadix,
                    title = stringResource(id = R.string.initial_radix)
                )
            }
            if (showTargetRadixChoice) {
                ChoiceDialog(
                    onClick = { choice -> selectedTargetRadix = choice },
                    onDismissRequest = { showTargetRadixChoice = false },
                    selectedRadix = selectedTargetRadix,
                    title = stringResource(id = R.string.target_radix)
                )
            }
        }
    )
}
