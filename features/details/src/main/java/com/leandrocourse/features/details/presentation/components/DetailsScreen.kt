package com.leandrocourse.features.details.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.leandrocourse.core.navigation.DetailsRoute
import com.leandrocourse.cryptorune.core.data.remote.R
import com.leandrocourse.features.details.di.DetailsModuleLoad
import com.leandrocourse.features.details.presentation.viewmodel.DetailsViewEffect
import com.leandrocourse.features.details.presentation.viewmodel.DetailsViewIntent
import com.leandrocourse.features.details.presentation.viewmodel.DetailsViewModel
import com.leandrocourse.libraries.arch.extension.openBrowser
import com.leandrocourse.libraries.arch.koin.koinlazy.KoinLazyModuleInitializer
import com.leandrocourse.libraries.arch.navigation.extension.LocalNavController
import com.leandrocourse.libraries.arch.viewmodel.extension.observeAsEvents
import com.leandrocourse.libraries.design.components.button.ButtonType
import com.leandrocourse.libraries.design.components.button.PlutoButtonComponent
import com.leandrocourse.libraries.design.theme.PlutoTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


internal const val WEBSITE_CLICKED_TEST_TAG = "WEBSITE_CLICKED_TEST_TAG"
internal const val ON_BACK_CLICKED_TEST_TAG = "ON_BACK_CLICKED_TEST_TAG"


@Composable
internal fun DetailsScreen(
    exchangeId: String,
    vm: DetailsViewModel? = null
) {
    KoinLazyModuleInitializer(DetailsModuleLoad)
    val viewModel = vm ?: koinViewModel { parametersOf(exchangeId) }
    val state by viewModel.state.collectAsState()
    DetailsObserve(viewModel)

    Scaffold(
        modifier = Modifier.padding(PlutoTheme.dimen.dp16),
        bottomBar = {
            PlutoButtonComponent(
                modifier = Modifier
                    .testTag(ON_BACK_CLICKED_TEST_TAG)
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                text = stringResource(R.string.common_go_back),
                buttonType = ButtonType.Primary,
                onClick = { viewModel.onViewIntent(DetailsViewIntent.OnBackClicked) }
            )
        },
        content = {
            DetailsContent(
                modifier = Modifier.padding(it),
                state = state,
                intent = viewModel::onViewIntent
            )
        }
    )
}

@Composable
private fun DetailsObserve(
    viewModel: DetailsViewModel,
) {
    val context = LocalContext.current
    val navController = LocalNavController.current

    viewModel.effect.observeAsEvents { effect ->
        when (effect) {
            is DetailsViewEffect.NavigateToExchanges -> {
                navController.popBackStack<DetailsRoute>(inclusive = true)
            }

            is DetailsViewEffect.NavigateToWebsite -> {
                context.openBrowser(effect.url)
            }
        }
    }
}
