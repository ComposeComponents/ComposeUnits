package cl.emilym.compose.units

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Double.px: Dp
    @Composable
    get() = with(LocalDensity.current) { this@px.toFloat().toDp() }
val Float.px: Dp
    @Composable
    get() = with(LocalDensity.current) { this@px.toDp() }
val Int.px: Dp
    @Composable
    get() = with(LocalDensity.current) { this@px.toDp() }