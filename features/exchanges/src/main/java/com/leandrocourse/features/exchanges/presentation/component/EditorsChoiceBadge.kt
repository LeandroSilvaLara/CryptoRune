package com.leandrocourse.features.exchanges.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.leandrocourse.cryptorune.core.data.remote.R
import com.leandrocourse.libraries.design.theme.PlutoTheme

@Composable
internal fun VolumeItemV2(modifier: Modifier = Modifier, label: String, value: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PlutoTheme.dimen.dp4)
    ) {
        Text(
            text = value,
            style = PlutoTheme.typography.titleMedium,
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
internal fun EditorsChoiceBadgeV2(modifier: Modifier = Modifier, shouldShow: Boolean) {
    if (!shouldShow) return

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = PlutoTheme.radius.medium))
            .background(MaterialTheme.colorScheme.primary)
            .padding(
                horizontal = PlutoTheme.dimen.dp12,
                vertical = PlutoTheme.dimen.dp4
            )
    ) {
        Text(
            text = stringResource(R.string.exchange_editors_choice),
            color = MaterialTheme.colorScheme.onPrimary,
            style = PlutoTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
    }
}