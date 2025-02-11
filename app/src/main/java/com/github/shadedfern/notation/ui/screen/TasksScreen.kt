package com.github.shadedfern.notation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.shadedfern.notation.R
import com.github.shadedfern.notation.ui.components.PrimaryButton
import com.github.shadedfern.notation.ui.components.SecondaryButton
import com.github.shadedfern.notation.ui.components.TextField
import com.github.shadedfern.notation.ui.components.TopBar
import com.github.shadedfern.notation.ui.components.tasks.TasksCard
import com.github.shadedfern.notation.ui.components.Title
import kotlin.random.Random

@Composable
fun TasksScreen(modifier: Modifier = Modifier) {

    var userInput by remember { mutableStateOf("") }
    var isError: Boolean by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }
    var radixPair by remember { mutableStateOf(radixChoice()) }
    var decimalNumber by remember { mutableIntStateOf(Random.nextInt(2, 512)) }

    val resultRight = stringResource(id = R.string.result_right)
    val resultWrong = stringResource(id = R.string.result_wrong)

    val (initialRadix, targetRadix) = radixPair
    var initialValue by remember { mutableStateOf(decimalNumber.toString(initialRadix)) }
    var targetValue by remember { mutableStateOf(decimalNumber.toString(targetRadix)) }

    var showHint by remember { mutableStateOf(false) }

    LaunchedEffect(decimalNumber, radixPair) {
        targetValue = decimalNumber.toString(targetRadix)
        initialValue = decimalNumber.toString(initialRadix)
    }

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
                        label = stringResource(id = R.string.label_tasks)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        PrimaryButton(
                            onClick = {
                                try {
                                    val answer = userInput.toInt(targetRadix)

                                    result = if (answer == decimalNumber) { resultRight } else { resultWrong }
                                    isError = false
                                } catch (e: NumberFormatException) {
                                    isError = true
                                    result = ""
                                }
                            },
                            stringResource(id = R.string.check_button)
                        )
                        SecondaryButton(
                            onClick = { showHint = true },
                            stringResource(id = R.string.hint_button)
                        )
                    }
                }
                Title(
                    titleText = stringResource(id = R.string.title_tasks),
                    subtitleText = stringResource(id = R.string.subtitle_tasks)
                )

                if (showHint) {
                    AlertDialog(
                        title = {
                            Text(stringResource(id = R.string.hint_title))
                        },
                        text = {
                            Column {
                                Text(targetValue)
                            }
                        },
                        onDismissRequest = { showHint = false },
                        confirmButton = {
                            FilledTonalButton(onClick = { showHint = false }) {
                                Text(stringResource(id = R.string.thanks_dialogue))
                            }
                        }
                    )
                }

                TasksCard(
                    initialValue = initialValue,
                    initialRadix = initialRadix,
                    targetRadix = targetRadix,
                    onRefreshClick = {
                        radixPair = radixChoice()
                        decimalNumber = Random.nextInt(2, 512)
                        val (initialRadix, _) = radixPair
                        userInput = ""
                        result = ""
                        isError = false
                        showHint = false
                    }
                )
            }
        }
    )
}

fun radixChoice(): Pair<Int, Int> {
    val radixes = listOf(2, 8, 10, 16)
    val initialRadix = radixes.random()
    val targetRadix = (radixes - initialRadix).random()
    return Pair(initialRadix, targetRadix)
}
