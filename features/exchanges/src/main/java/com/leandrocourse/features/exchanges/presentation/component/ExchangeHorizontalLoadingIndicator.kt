package com.leandrocourse.features.exchanges.presentation.component

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leandrocourse.libraries.design.skeleton.shimmerBackground
import com.leandrocourse.libraries.design.theme.PlutoTheme
import com.leandrocourse.libraries.design.theme.tokens.PlutoDimens
import com.leandrocourse.libraries.design.theme.tokens.PlutoElevation

fun Modifier.shimmerBackground(shape: Shape): Modifier = composed {
    val shimmerColors = listOf(
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f),
    )

    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1200f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerTranslateAnimation"
    )

    this.background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        ),
        shape = shape as androidx.compose.ui.graphics.Shape
    )
}

@Composable
internal fun PortfolioCardSkeleton(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(PlutoTheme.radius.large)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmerBackground(shape = RoundedCornerShape(PlutoTheme.radius.large))
        )
    }
}

@Composable
internal fun ExchangeItemSkeleton(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(PlutoTheme.radius.large),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(PlutoElevation.max),
            verticalArrangement = Arrangement.spacedBy(PlutoElevation.ultra)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(PlutoDimens.dp52)
                        .shimmerBackground(shape = CircleShape)
                )
                Spacer(Modifier.width(PlutoElevation.ultra))
                Column(verticalArrangement = Arrangement.spacedBy(PlutoElevation.extra)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(PlutoDimens.dp20)
                            .shimmerBackground(shape = RoundedCornerShape(PlutoDimens.dp4))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(PlutoElevation.max)
                            .shimmerBackground(shape = RoundedCornerShape(PlutoDimens.dp14))
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(PlutoElevation.extra)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(PlutoDimens.dp14)
                        .shimmerBackground(shape = RoundedCornerShape(PlutoDimens.dp4))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.size(PlutoDimens.dp52, PlutoDimens.dp24).shimmerBackground(RoundedCornerShape(4.dp)))
                    Box(modifier = Modifier.size(PlutoDimens.dp52, PlutoDimens.dp24).shimmerBackground(RoundedCornerShape(4.dp)))
                    Box(modifier = Modifier.size(PlutoDimens.dp52, PlutoDimens.dp24).shimmerBackground(RoundedCornerShape(4.dp)))
                }
            }
        }
    }
}


@Composable
internal fun ExchangesLoadingSkeleton(modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(PlutoTheme.dimen.dp8),
        columns = StaggeredGridCells.Fixed(count = 2),
        verticalItemSpacing = PlutoTheme.dimen.dp8,
        horizontalArrangement = Arrangement.spacedBy(PlutoTheme.dimen.dp8),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            PortfolioCardSkeleton(
                modifier = Modifier.padding(bottom = PlutoTheme.dimen.dp8)
            )
        }

        items(6) {
            ExchangeItemSkeleton()
        }
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExchangesLoadingSkeletonPreview() {
    PlutoTheme(darkTheme = true) {
        ExchangesLoadingSkeleton()
    }
}