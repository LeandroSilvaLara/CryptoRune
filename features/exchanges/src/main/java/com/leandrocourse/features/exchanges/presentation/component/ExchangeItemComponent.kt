package com.leandrocourse.features.exchanges.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.cryptorune.core.data.remote.R
import com.leandrocourse.libraries.design.theme.PlutoTheme
import com.leandrocourse.libraries.design.theme.tokens.PlutoDimens
import com.leandrocourse.libraries.design.theme.tokens.lightRed
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.shimmer


@Composable
internal fun ExchangeItemComponent(
    modifier: Modifier = Modifier,
    exchange: Exchange,
    itemClicked: (Exchange) -> Unit
) {

    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.98f else 1f, label = "ScaleAnimation")

    Box(
        modifier = modifier
            .scale(scale)
            .pointerInput(Unit) {
                while (true) {
                    awaitPointerEventScope {
                        awaitFirstDown(requireUnconsumed = false)
                        isPressed = true
                        waitForUpOrCancellation()
                        isPressed = false
                    }
                }
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { itemClicked(exchange) }
                ),
            shape = RoundedCornerShape(PlutoTheme.radius.large),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f),
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                            )
                        )
                    )
                    .border(
                        width = PlutoDimens.dp2,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                                Color.Transparent
                            )
                        ),
                        shape = RoundedCornerShape(PlutoTheme.radius.large)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(PlutoTheme.dimen.dp16)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(PlutoTheme.dimen.dp48)
                                .clip(CircleShape)
                                .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), CircleShape),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(exchange.iconUrl).crossfade(true).build(),
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.illu_placeholder),
                            error = painterResource(R.drawable.illu_placeholder),
                            contentDescription = null,
                        )
                        Spacer(Modifier.width(PlutoTheme.dimen.dp12))
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = exchange.name,
                                style = PlutoTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(PlutoTheme.dimen.dp4))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = exchange.exchangeId,
                                    style = PlutoTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }

                    Divider(
                        modifier = Modifier.padding(vertical = PlutoTheme.dimen.dp12),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    )

                    Text(
                        text = stringResource(id = R.string.exchange_volume_usd_title),
                        style = PlutoTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.padding(start = PlutoTheme.dimen.dp4, bottom = PlutoTheme.dimen.dp8)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        VolumeItemV2(label = stringResource(id = R.string.exchange_volume_1hr_label), value = exchange.volume1hrsUsd)
                        VolumeItemV2(label = stringResource(id = R.string.exchange_volume_1day_label), value = exchange.volume1dayUsd)
                        VolumeItemV2(label = stringResource(id = R.string.exchange_volume_1mth_label), value = exchange.volume1mthUsd)
                    }
                }

                EditorsChoiceBadgeV2(
                    modifier = Modifier.align(Alignment.TopEnd),
                    shouldShow = exchange.isEditorChoice
                )
            }
        }
    }

}

@Composable
private fun RowScope.VolumeItemV2(label: String, value: String) {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PlutoDimens.dp4)
    ) {
        Text(
            text = formatVolume(value),
            style = PlutoTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = PlutoTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
internal fun ColumnScope.EditorsChoiceBadge(modifier: Modifier = Modifier, shouldShow: Boolean) {
    if (!shouldShow) return

    CompositionLocalProvider(
        LocalShimmerTheme provides editorsChoiceBadgeTheme,
    ) {
        Box(
            modifier = modifier
                .align(Alignment.End)
                .clip(RoundedCornerShape(topStart = PlutoTheme.radius.medium))
                .background(
                    color = lightRed,
                    shape = RoundedCornerShape(topStart = PlutoTheme.radius.medium)
                )
                .shimmer()
                .padding(
                    horizontal = PlutoTheme.dimen.dp8,
                    vertical = PlutoTheme.dimen.dp4
                )
        ) {
            Text(
                text = stringResource(R.string.exchange_editors_choice),
                color = Color.White,
                style = PlutoTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private val editorsChoiceBadgeTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 600,
            delayMillis = 2_500,
            easing = LinearEasing,
        ),
    ),
    blendMode = BlendMode.SrcOver,
    rotation = 25f,
    shaderColors = listOf(
        Color.White.copy(alpha = 0.2f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.2f),
    ),
    shaderColorStops = null,
    shimmerWidth = 400.dp,
)

@Preview
@Composable
private fun ExchangeItemComponentPreview() {
    val exchange = Exchange(
        exchangeId = "MERCADOBITCOIN",
        website = "https://www.mercadobitcoin.com.br/",
        name = "Mercado Bitcoin",
        dataQuoteStart = "15/03/17 00:00",
        dataQuoteEnd = "24/08/24 00:00",
        dataOrderBookStart = "14/03/17 20:05",
        dataOrderBookEnd = "06/07/23 00:00",
        dataTradeStart = "07/03/17 00:00",
        dataTradeEnd = "24/08/24 00:00",
        dataSymbolsCount = "462",
        volume1hrsUsd = "228.67",
        volume1dayUsd = "829.7K",
        volume1mthUsd = "192.7M",
        iconUrl = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/5fbfbd742fb64c67a3963ebd7265f9f3.png",
        isEditorChoice = true
    )
    PlutoTheme(
        darkTheme = true
    ) {
        ExchangeItemComponent(
            exchange = exchange,
            itemClicked = {}
        )
    }
}
