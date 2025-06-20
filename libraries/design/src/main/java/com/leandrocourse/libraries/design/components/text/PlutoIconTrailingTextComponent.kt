package com.leandrocourse.libraries.design.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.leandrocourse.libraries.design.components.lorem.loremIpsum
import com.leandrocourse.libraries.design.theme.PlutoTheme
import com.leandrocourse.libraries.design.theme.tokens.PlutoDimens
import java.util.UUID
import kotlin.math.roundToInt

/**
 * A composable function that displays a text with an icon trailing it.
 *
 * @param modifier A [Modifier] for this composable. Default is [Modifier].
 * @param text The text to be displayed.
 * @param textStyle The style to be applied to the text. Default is [TextStyle].
 * @param textAlign The alignment of the text. Default is [TextAlign.Center].
 * @param color The color of the text. Default is [Color.Unspecified].
 * @param iconPadding The padding between the text and the icon. Default is [PlutoDimens.dp2].
 * @param iconContent A composable lambda that defines the icon to be displayed.
 */

@Composable
fun PlutoIconTrailingTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(),
    textAlign: TextAlign = TextAlign.Center,
    color: Color = Color.Unspecified,
    iconPadding: Dp = PlutoDimens.dp2,
    iconContent: @Composable () -> Unit,
) {
    val id = UUID.randomUUID().toString()
    val buildAnnotatedString = buildAnnotatedString {
        append(text)
        append(" ".repeat((iconPadding / 4.dp).roundToInt()))
        appendInlineContent(id)
    }
    val inlineContent = mapOf(
        Pair(
            id,
            InlineTextContent(
                Placeholder(
                    width = PlutoTheme.fontSizing.scaleSm,
                    height = PlutoTheme.fontSizing.scaleSm,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Top
                )
            ) {
                iconContent()
            }
        )
    )

    Text(
        modifier = modifier,
        text = buildAnnotatedString,
        inlineContent = inlineContent,
        textAlign = textAlign,
        style = textStyle,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
private fun IconTrailingTextComponentPreview() {
    PlutoTheme(
        darkTheme = false
    ) {
        PlutoIconTrailingTextComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PlutoTheme.dimen.dp8),
            text = loremIpsum { 10 },
            textStyle = PlutoTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = null
                )
            }
        }
    }
}
