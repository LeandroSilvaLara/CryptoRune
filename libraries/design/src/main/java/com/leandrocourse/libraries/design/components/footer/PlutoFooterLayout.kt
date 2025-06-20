package com.leandrocourse.libraries.design.components.footer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.leandrocourse.libraries.design.components.button.ButtonType
import com.leandrocourse.libraries.design.components.button.PlutoButtonComponent
import com.leandrocourse.libraries.design.components.lorem.loremIpsum
import com.leandrocourse.libraries.design.theme.PlutoTheme

/**
 * A composable function that provides a footer layout with a gradient effect at the top.
 *
 * @param content A composable lambda that defines the content to be displayed within the footer.
 *                It is scoped to a [ColumnScope] to allow for column-based layout.
 *
 * This function does not return a value. It is used to structure the UI layout.
 */

@Composable
fun PlutoFooterLayout(
    content: @Composable ColumnScope.() -> Unit
) {
    val color = MaterialTheme.colorScheme.onBackground
    val height = PlutoTheme.dimen.dp4

    Column {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
        ) {
            val gradient = Brush.verticalGradient(
                colors = listOf(color.copy(alpha = 0.05f), Color.Transparent),
                startY = size.height,
                endY = 0f
            )
            drawRect(brush = gradient)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = height,
                    start = PlutoTheme.dimen.dp16,
                    end = PlutoTheme.dimen.dp16,
                    bottom = PlutoTheme.dimen.dp16
                )
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlutoFooterLayoutPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        PlutoFooterLayout {
            Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp8))
            PlutoButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                text = loremIpsum { 2 },
                buttonType = ButtonType.Primary
            )
            Spacer(modifier = Modifier.padding(PlutoTheme.dimen.dp8))
            PlutoButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                text = loremIpsum { 2 },
                buttonType = ButtonType.Secondary
            )
        }
    }
}