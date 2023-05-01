package com.clean.architecture.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clean.architecture.ui.theme.Secondary200
import com.clean.architecture.ui.theme.TooltipTriangle
import com.popovanton0.heartswitch.HeartSwitch
import com.popovanton0.heartswitch.HeartSwitchColors

@Composable
fun HeartToggle(modifier: Modifier = Modifier, toggled: (Boolean) -> Unit) {
    var state by remember { mutableStateOf(true) }
    HeartSwitch(
        checked = state,
        width = 150.dp,
        onCheckedChange = {
            state = it
            toggled(state)
        }, colors = HeartSwitchColors(
            uncheckedThumbColor = TooltipTriangle,
            uncheckedTrackBorderColor = Secondary200
        ),
        modifier = modifier
    )
}

@Preview
@Composable
fun previewBasicUsage() {
    HeartToggle {

    }
}