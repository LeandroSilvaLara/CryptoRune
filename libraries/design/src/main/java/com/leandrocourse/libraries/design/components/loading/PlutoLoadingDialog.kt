package com.leandrocourse.libraries.design.components.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.leandrocourse.cryptorune.libraries.design.R
import com.leandrocourse.libraries.design.accessibility.clearAndSetSemantics
import com.leandrocourse.libraries.design.theme.PlutoTheme

/**
 * Displays a loading dialog with an optional label.
 *
 * This composable function shows a dialog containing a circular progress indicator.
 * An optional text label can be displayed below the indicator.
 *
 * @param shouldShowDialog A Boolean flag indicating whether the dialog should be displayed.
 * @param shouldShowLabel A Boolean flag indicating whether a text label should be shown below the progress indicator.
 */

@Composable
fun PlutoLoadingDialog(
    shouldShowDialog: Boolean,
    shouldShowLabel: Boolean,
) {

    if (shouldShowDialog) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Surface(
                shape = RoundedCornerShape(PlutoTheme.radius.large),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .width(PlutoTheme.dimen.dp200)
                    .wrapContentHeight()
                    .padding(PlutoTheme.dimen.dp16)
            ) {
                Column(
                    modifier = Modifier
                        .clearAndSetSemantics {
                            this.liveRegion = LiveRegionMode.Assertive
                            this.contentDescription = stringResource(
                                R.string.common_desc_loading_alert_message
                            )
                        }
                        .padding(PlutoTheme.dimen.dp16),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    if (shouldShowLabel) {
                        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp16))
                        Text(
                            text = stringResource(R.string.common_wait),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlutoLoadingDialogPreview() {
    PlutoLoadingDialog(
        shouldShowDialog = true,
        shouldShowLabel = true
    )
}
