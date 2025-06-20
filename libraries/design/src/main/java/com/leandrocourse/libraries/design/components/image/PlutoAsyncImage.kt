package com.leandrocourse.libraries.design.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage

/**
 * A composable function that loads and displays an image asynchronously using Coil.
 *
 * @param modifier A [Modifier] for styling or layout adjustments. Defaults to [Modifier].
 * @param model The data model representing the image to be loaded. This can be a URL, URI, or any other supported type.
 * @param content A composable lambda that provides the UI to display based on the loading state.
 *                The lambda receives a Boolean indicating if the image is loading or has encountered an error,
 *                and an [AsyncImagePainter] for rendering the image.
 */

@Composable
fun PlutoAsyncImage(
    modifier: Modifier = Modifier,
    model: Any?,
    content: @Composable (Boolean, AsyncImagePainter) -> Unit
) {

    SubcomposeAsyncImage(
        modifier = modifier,
        model = model,
        contentDescription = null
    ) {

        val state = painter.state
        content(
            state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error,
            painter
        )
    }
}