package com.leandrocourse.features.details.presentation.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.leandrocourse.libraries.design.theme.PlutoTheme


@Composable
internal fun DetailsVolumeBarComponent(
    label: String,
    value: String,
    color: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = PlutoTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = PlutoTheme.stroke.line,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
        Text(
            modifier = Modifier
                .padding(horizontal = PlutoTheme.dimen.dp4)
                .background(color = color, shape = MaterialTheme.shapes.small)
                .padding(horizontal = PlutoTheme.dimen.dp8),
            text = value,
            style = PlutoTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}
