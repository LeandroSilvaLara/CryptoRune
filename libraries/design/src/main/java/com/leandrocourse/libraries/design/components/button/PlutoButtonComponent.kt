package com.leandrocourse.libraries.design.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.leandrocourse.libraries.design.components.button.model.ButtonState
import com.leandrocourse.libraries.design.theme.PlutoTheme

/**
 * A composable function that renders a button based on the specified [ButtonType].
 *
 * @param modifier A [Modifier] for styling the button, default is [Modifier].
 * @param text The text to be displayed on the button.
 * @param buttonState The state of the button, which determines if it is enabled or disabled. Default is [ButtonState.Enabled].
 * @param clickType The type of click action to be used, default is [ClickType.Debounce].
 * @param buttonType The type of button to render, which can be [ButtonType.Primary], [ButtonType.Secondary], or [ButtonType.Tertiary].
 * @param onClick A lambda function to be executed when the button is clicked. Default is an empty lambda.
 */

@Composable
fun PlutoButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    buttonState: ButtonState = ButtonState.Enabled,
    clickType: ClickType = ClickType.Debounce,
    buttonType: ButtonType,
    onClick: () -> Unit = {}
) {

    when (buttonType) {
        ButtonType.Primary -> {
            PlutoPrimaryButton(
                modifier = modifier,
                text = text,
                buttonState = buttonState,
                clickType = clickType,
                onClick = onClick
            )
        }

        ButtonType.Secondary -> {
            PlutoSecondaryButton(
                modifier = modifier,
                text = text,
                buttonState = buttonState,
                clickType = clickType,
                onClick = onClick
            )
        }

        ButtonType.Tertiary -> {
            PlutoTertiaryButton(
                modifier = modifier,
                text = text,
                buttonState = buttonState,
                clickType = clickType,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun PlutoPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonState: ButtonState,
    clickType: ClickType,
    onClick: () -> Unit
) {

    Button(
        enabled = buttonState.isEnabled,
        onClick = rememberClickAction(clickType = clickType, action = onClick),
        modifier = modifier.height(PlutoTheme.dimen.dp52),
        shape = RoundedCornerShape(PlutoTheme.radius.medium),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    ) {
        TextComponent(text = text)
    }
}

@Composable
private fun PlutoSecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonState: ButtonState,
    clickType: ClickType,
    onClick: () -> Unit
) {

    OutlinedButton(
        enabled = buttonState.isEnabled,
        onClick = rememberClickAction(clickType = clickType, action = onClick),
        modifier = modifier.height(PlutoTheme.dimen.dp52),
        shape = RoundedCornerShape(PlutoTheme.radius.medium),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        border = BorderStroke(
            width = PlutoTheme.stroke.line,
            color = MaterialTheme.colorScheme.secondary
        )
    ) {
        TextComponent(text = text)
    }
}

@Composable
private fun PlutoTertiaryButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonState: ButtonState,
    clickType: ClickType,
    onClick: () -> Unit
) {

    TextButton(
        enabled = buttonState.isEnabled,
        onClick = rememberClickAction(clickType = clickType, action = onClick),
        modifier = modifier.height(PlutoTheme.dimen.dp52),
        shape = RoundedCornerShape(PlutoTheme.radius.medium),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        TextComponent(text = text)
    }
}

@Composable
internal fun TextComponent(
    modifier: Modifier = Modifier,
    text: String
) {

    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center
    )
}


enum class ButtonType {
    Primary,
    Secondary,
    Tertiary
}

@Preview(showBackground = true)
@Composable
private fun ButtonsPreview() {
    Column(
        modifier = Modifier.padding(PlutoTheme.dimen.dp16),
        verticalArrangement = Arrangement.spacedBy(PlutoTheme.dimen.dp16)
    ) {
        PlutoButtonComponent(
            modifier = Modifier.fillMaxWidth(),
            buttonType = ButtonType.Primary,
            text = "Primary Button"
        )
        PlutoButtonComponent(
            modifier = Modifier.fillMaxWidth(),
            buttonType = ButtonType.Secondary,
            text = "Secondary Button", onClick = {}
        )
        PlutoButtonComponent(
            modifier = Modifier.fillMaxWidth(),
            buttonType = ButtonType.Tertiary,
            text = "Tertiary Button", onClick = {}
        )
    }
}
