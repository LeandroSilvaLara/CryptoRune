package com.leandrocourse.features.exchanges.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
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
import com.leandrocourse.features.exchanges.presentation.screen.EXCHANGES_ITEM_TEST_TAG
import com.leandrocourse.libraries.design.extension.clickable
import com.leandrocourse.libraries.design.theme.PlutoTheme
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

    Card(
        modifier = Modifier
            .testTag("${EXCHANGES_ITEM_TEST_TAG}_${exchange.exchangeId}")
            .clickable { itemClicked(exchange) },
        shape = RoundedCornerShape(PlutoTheme.radius.medium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(PlutoTheme.dimen.dp8)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                AsyncImage(
                    modifier = modifier
                        .size(PlutoTheme.dimen.dp40)
                        .clip(RoundedCornerShape(PlutoTheme.radius.small)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(exchange.iconUrl)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.illu_placeholder),
                    error = painterResource(R.drawable.illu_placeholder),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        text = exchange.name,
                        overflow = TextOverflow.Ellipsis,
                        style = PlutoTheme.typography.titleMedium,
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        text = "ID: ${exchange.exchangeId}",
                        overflow = TextOverflow.Ellipsis,
                        style = PlutoTheme.typography.labelSmall,
                    )
                }
            }
            Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
            Text(
                text = "USD Volume",
                style = PlutoTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "1hr",
                    style = PlutoTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = PlutoTheme.dimen.dp4)
                        .background(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.primary
                        )
                        .padding(horizontal = PlutoTheme.dimen.dp8),
                    text = exchange.volume1hrsUsd,
                    style = PlutoTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }
        EditorsChoiceBadge(
            shouldShow = exchange.isEditorChoice
        )
    }
}

@Composable
internal fun ColumnScope.EditorsChoiceBadge(
    modifier: Modifier = Modifier,
    shouldShow: Boolean
) {
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
                text = stringResource(R.string.editors_choice),
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
