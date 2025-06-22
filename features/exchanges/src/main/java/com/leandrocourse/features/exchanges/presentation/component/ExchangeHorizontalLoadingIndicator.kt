package com.leandrocourse.features.exchanges.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
internal fun ExchangeHorizontalLoadingIndicator(
    modifier: Modifier = Modifier,
    shouldShow: Boolean
) {
    if (shouldShow) {
        LinearProgressIndicator(
            modifier = modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExchangeHorizontalLoadingIndicatorPreview() {
    ExchangeHorizontalLoadingIndicator(
        shouldShow = true
    )
}