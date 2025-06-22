package com.leandrocourse.features.exchanges.presentation.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.leandrocourse.libraries.design.theme.PlutoTheme


private const val TRANSITION_LABEL_SCALE = "ScaleTransition"
private const val TRANSITION_LABEL_ALPHA = "AlphaTransition"
private const val INITIAL_TRANSITION_VALUE = 1f
private const val ANIMATION_DURATION_MILLIS = 1000

@Composable
internal fun PulseCircleIndicatorComponent(
    modifier: Modifier = Modifier,
    shouldShow: Boolean,
) {

    if (shouldShow) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            PulsingCircle(
                color = MaterialTheme.colorScheme.secondary,
                size = PlutoTheme.dimen.dp100
            )
            PulsingCircle(
                color = MaterialTheme.colorScheme.secondary,
                size = PlutoTheme.dimen.dp48
            )
        }
    }
}

@Composable
private fun PulsingCircle(
    modifier: Modifier = Modifier,
    color: Color,
    size: Dp,
) {
    val scale = infiniteTransition(targetValue = 2f)
    val alpha = infiniteTransition(targetValue = 0f)

    Box(
        modifier = modifier
            .size(size)
            .scale(scale)
            .background(
                color = color.copy(alpha = alpha),
                shape = androidx.compose.foundation.shape.CircleShape
            )
    )
}

@Composable
private fun infiniteTransition(
    targetValue: Float
): Float {
    val infiniteTransition = rememberInfiniteTransition(TRANSITION_LABEL_SCALE)

    val transition by infiniteTransition.animateFloat(
        initialValue = INITIAL_TRANSITION_VALUE,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = ANIMATION_DURATION_MILLIS,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = TRANSITION_LABEL_ALPHA
    )

    return transition
}
