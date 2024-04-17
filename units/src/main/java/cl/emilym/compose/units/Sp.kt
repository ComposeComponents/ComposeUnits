package cl.emilym.compose.units

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

var LocalBaseSp = staticCompositionLocalOf { 16.sp }

/**
 * Relative Scalable Pixels
 */
val Double.rsp: TextUnit
    @Composable
    get() = LocalBaseSp.current * this
/**
 * Relative Scalable Pixels
 */
val Float.rsp: TextUnit
    @Composable
    get() = LocalBaseSp.current * this
/**
 * Relative Scalable Pixels
 */
val Int.rsp: TextUnit
    @Composable
    get() = LocalBaseSp.current * this