package com.leandrocourse.libraries.design.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.leandrocourse.libraries.design.components.button.ClickType
import com.leandrocourse.libraries.design.components.button.rememberClickAction
import com.leandrocourse.libraries.design.theme.tokens.PlutoRadius

/**
 * Adds a clickable modifier to a composable with a rounded corner shape and debouncing capability.
 *
 * @param enabled Whether the clickable is enabled. Defaults to true.
 * @param onClickLabel Optional label for accessibility services to announce when the element is clicked.
 * @param role The type of user interface element. Defaults to null.
 * @param clickType The type of click handling, such as debounce. Defaults to ClickType.Debounce.
 * @param onClicked The lambda to be executed when the element is clicked.
 * @return A [Modifier] that makes the composable clickable with the specified properties.
 */
@Composable
fun Modifier.clickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    clickType: ClickType = ClickType.Debounce,
    onClicked: () -> Unit
): Modifier {
    return this
        .clip(RoundedCornerShape(PlutoRadius.medium))
        .clickable(
            enabled = enabled,
            onClickLabel = onClickLabel,
            role = role,
            onClick = rememberClickAction(clickType = clickType, action = onClicked)
        )
}

@Composable
fun Float.toDp(): Dp {
    val density = LocalDensity.current.density
    return (this / density).dp
}

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return value * density
}