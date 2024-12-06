package com.github.shadedfern.notation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.shadedfern.notation.R
import com.github.shadedfern.notation.ui.screen.ConverterScreen
import com.github.shadedfern.notation.ui.screen.TasksScreen

@Composable
fun NavigationBar() {
    var selectedTab by remember { mutableStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            androidx.compose.material3.NavigationBar() {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Refresh, contentDescription = null) },
                    label = { stringResource(id = R.string.tasks_navigation) },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.SwapHoriz, contentDescription = null) },
                    label = { stringResource(id = R.string.converter_navigation) },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
        }
    ) { innerPadding ->
        when (selectedTab) {
            0 -> TasksScreen(modifier = Modifier.padding(innerPadding))
            1 -> ConverterScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}