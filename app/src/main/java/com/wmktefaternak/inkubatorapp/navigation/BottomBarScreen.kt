package com.wmktefaternak.inkubatorapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Monitor
import androidx.compose.material.icons.filled.Tune
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Monitoring: BottomBarScreen(
        route = "monitor",
        title = "Monitoring",
        icon = Icons.Default.Monitor
    )
    object Controlling: BottomBarScreen(
        route = "control",
        title = "Controlling",
        icon = Icons.Default.Tune
    )
    object Form: BottomBarScreen(
        route = "form",
        title = "Form",
        icon = Icons.Default.FormatListBulleted
    )
}