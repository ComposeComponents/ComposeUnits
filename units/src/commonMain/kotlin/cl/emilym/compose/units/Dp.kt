package cl.emilym.compose.units

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

var LocalBaseDp = staticCompositionLocalOf { 16.dp }

/**
 * Relative Density Pixels
 */
val Double.rdp: Dp
    @Composable
    get() = LocalBaseDp.current * this.toFloat()
/**
 * Relative Density Pixels
 */
val Float.rdp: Dp
    @Composable
    get() = LocalBaseDp.current * this
/**
 * Relative Density Pixels
 */
val Int.rdp: Dp
    @Composable
    get() = LocalBaseDp.current * this