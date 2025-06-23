package com.leandrocourse.features.details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.cryptorune.core.data.remote.R
import com.leandrocourse.features.details.presentation.components.content.DetailsVolumeBarComponent
import com.leandrocourse.features.details.presentation.components.content.SparkLineChart
import com.leandrocourse.features.details.presentation.viewmodel.DetailsViewIntent
import com.leandrocourse.features.details.presentation.viewmodel.DetailsViewState
import com.leandrocourse.libraries.design.theme.PlutoTheme
import com.leandrocourse.libraries.design.theme.tokens.lightRed


@Composable
internal fun DetailsContent(
    modifier: Modifier = Modifier,
    state: DetailsViewState,
    intent: (DetailsViewIntent) -> Unit,
) {


    LaunchedEffect(Unit) {
        intent(DetailsViewIntent.OnInitView)
    }
    state.exchange?.let { exchange ->
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(PlutoTheme.radius.large),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PlutoTheme.dimen.dp16)
            ) {
                Header(exchange = exchange)
                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp16))
                Text(
                    text = stringResource(R.string.usd_volume),
                    style = PlutoTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                DetailsVolumeBarComponent(
                    label = "1hr",
                    value = exchange.volume1hrsUsd,
                    color = MaterialTheme.colorScheme.primary
                )
                DetailsVolumeBarComponent(
                    label = "1day",
                    value = exchange.volume1dayUsd,
                    color = MaterialTheme.colorScheme.secondary
                )
                DetailsVolumeBarComponent(
                    label = "1mth",
                    value = exchange.volume1mthUsd,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp16))

                ChartSection(
                    title = "Volume History (30d)",
                    data = state.historicalData,
                    graphColor = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp24))

                InfoPair(
                    title = "Website",
                    subtitle = exchange.website,
                    onClick = { intent(DetailsViewIntent.OnNavigateToWebsite(exchange.website)) }
                )
            }
            EditorsChoiceBadge(
                shouldShow = exchange.isEditorChoice
            )
        }
    }
}

@Composable
private fun Header(
    exchange: Exchange,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            modifier = Modifier
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
                style = PlutoTheme.typography.titleLarge,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                text = "ID: ${exchange.exchangeId}",
                overflow = TextOverflow.Ellipsis,
                style = PlutoTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun ColumnScope.InfoPair(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {

    Text(
        maxLines = 1,
        text = title,
        overflow = TextOverflow.Ellipsis,
        style = PlutoTheme.typography.bodyLarge,
    )
    Text(
        modifier = Modifier
            .testTag(WEBSITE_CLICKED_TEST_TAG)
            .clickable { onClick() },
        maxLines = 1,
        text = subtitle,
        overflow = TextOverflow.Ellipsis,
        style = PlutoTheme.typography.labelLarge.copy(
            textDecoration = TextDecoration.Underline
        ),
        color = PlutoTheme.colors.blueLink
    )
}

@Composable
private fun ColumnScope.EditorsChoiceBadge(
    modifier: Modifier = Modifier,
    shouldShow: Boolean
) {
    if (!shouldShow) return

    Box(
        modifier = modifier
            .align(Alignment.End)
            .background(
                color = lightRed,
                shape = RoundedCornerShape(topStart = PlutoTheme.radius.large)
            )
            .padding(
                horizontal = PlutoTheme.dimen.dp12,
                vertical = PlutoTheme.dimen.dp8
            )
    ) {
        Text(
            text = stringResource(R.string.editors_choice),
            color = Color.White,
            style = PlutoTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
private fun ChartSection(
    modifier: Modifier = Modifier,
    title: String,
    data: List<Float>,
    graphColor: Color
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = PlutoTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
        Card(
            modifier = Modifier.fillMaxWidth().height(150.dp),
            shape = RoundedCornerShape(PlutoTheme.radius.medium),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = graphColor
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            SparkLineChart(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                data = data,
                graphColor = graphColor
            )
        }
    }
}

@Preview
@Composable
private fun DetailsContentPreview() {
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
    val state = DetailsViewState(
        exchangeId = "MERCADOBITCOIN",
        exchange = exchange
    )
    PlutoTheme(
        darkTheme = false
    ) {
        DetailsContent(
            state = state,
            intent = {},
        )
    }
}
