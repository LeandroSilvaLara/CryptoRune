package com.leandrocourse.libraries.design.components.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.leandrocourse.libraries.design.accessibility.clearAndSetSemantics
import com.leandrocourse.libraries.design.components.lorem.loremIpsum
import com.leandrocourse.libraries.design.theme.PlutoTheme

/**
 * A composable function that displays a customizable alert dialog.
 *
 * @param contentDescription An optional description of the dialog's content for accessibility purposes.
 * @param shouldShowDialog A boolean flag indicating whether the dialog should be displayed.
 * @param onDismissRequest A lambda function that is called when the dialog is dismissed.
 * @param properties The properties used to configure the dialog's behavior and appearance.
 * @param icon A composable function that defines the icon to be displayed in the dialog.
 * @param title A composable function that defines the title of the dialog.
 * @param text A composable function that defines the main text content of the dialog.
 * @param confirmButton A composable function that defines the confirm button in the dialog.
 * @param dismissButton A composable function that defines the dismiss button in the dialog.
 */

@Composable
fun PlutoAlertDialogComponent(
    contentDescription: String? = null,
    shouldShowDialog: Boolean,
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(),
    icon: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
    confirmButton: @Composable () -> Unit = {},
    dismissButton: @Composable () -> Unit = {},
) {

    if (shouldShowDialog) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PlutoTheme.dimen.dp16),
                shape = RoundedCornerShape(PlutoTheme.radius.large)
            ) {
                Column(
                    modifier = Modifier
                        .padding(PlutoTheme.dimen.dp16)
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp4))
                    Column(
                        modifier = Modifier.clearAndSetSemantics {
                            this.contentDescription = contentDescription.orEmpty()
                        },
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        icon()
                        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
                        CompositionLocalProvider(
                            LocalTextStyle provides PlutoTheme.typography.titleLarge,
                            content = title
                        )
                        Spacer(modifier = Modifier.padding(PlutoTheme.dimen.dp4))
                        CompositionLocalProvider(
                            LocalTextStyle provides PlutoTheme.typography.bodyMedium,
                            content = text
                        )
                    }
                    Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp24))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        dismissButton()
                        Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
                        confirmButton()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlutoAlertDialogComponentPreview() {
    PlutoAlertDialogComponent(
        shouldShowDialog = true,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                tint = PlutoTheme.text.colorPlaceholder,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = loremIpsum { 2 },
            )
        },
        text = {
            Text(
                text = loremIpsum { 10 },
            )
        },
        dismissButton = {
            TextButton(
                onClick = {}
            ) {
                Text("Cancel")
            }
        },
        confirmButton = {
            Button(
                onClick = {}
            ) {
                Text("Confirm")
            }
        }
    )
}
