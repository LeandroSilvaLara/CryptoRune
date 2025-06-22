package com.leandrocourse.features.exchanges.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec.RawRes
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.leandrocourse.cryptorune.core.data.remote.R
import com.leandrocourse.features.exchanges.presentation.screen.EMPTY_SCREE_DESCRIPTION_TEST_TAG
import com.leandrocourse.features.exchanges.presentation.screen.EMPTY_SCREE_ILLU_TEST_TAG
import com.leandrocourse.features.exchanges.presentation.screen.EMPTY_SCREE_TRY_AGAIN_TEST_TAG
import com.leandrocourse.libraries.design.components.button.ButtonType
import com.leandrocourse.libraries.design.components.button.PlutoButtonComponent
import com.leandrocourse.libraries.design.theme.PlutoTheme


@Composable
internal fun ExchangeEmptyScreenComponent(
    modifier: Modifier = Modifier,
    shouldShowEmptyScreen: Boolean,
    onTryAgainClicked: () -> Unit
) {
    val composition by rememberLottieComposition(RawRes(R.raw.astronaut))
    if (!shouldShowEmptyScreen) return

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                modifier = Modifier
                    .testTag(EMPTY_SCREE_ILLU_TEST_TAG)
                    .size(PlutoTheme.dimen.dp200),
                iterations = LottieConstants.IterateForever,
                composition = composition
            )
            Text(
                modifier = Modifier
                    .testTag(EMPTY_SCREE_DESCRIPTION_TEST_TAG)
                    .padding(horizontal = PlutoTheme.dimen.dp32)
                    .offset(y = -PlutoTheme.dimen.dp24),
                text = stringResource(R.string.exchange_empty_screen),
                textAlign = TextAlign.Center,
                style = PlutoTheme.typography.bodyLarge,
                color = PlutoTheme.text.colorPrimary
            )
            Spacer(modifier = Modifier.size(PlutoTheme.dimen.dp48))
            PlutoButtonComponent(
                modifier = Modifier.testTag(EMPTY_SCREE_TRY_AGAIN_TEST_TAG),
                text = stringResource(R.string.common_try_again),
                buttonType = ButtonType.Tertiary,
                onClick = onTryAgainClicked
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExchangeEmptyScreenComponentPreview() {
    ExchangeEmptyScreenComponent(
        shouldShowEmptyScreen = true,
        onTryAgainClicked = {}
    )
}
