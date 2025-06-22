package com.leandrocourse.features.exchanges.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.leandrocourse.core.navigation.DetailsNavigation
import com.leandrocourse.core.navigation.DetailsRoute
import com.leandrocourse.features.exchanges.di.ExchangesModuleLoad
import com.leandrocourse.features.exchanges.presentation.viewmodel.ExchangesViewEffect
import com.leandrocourse.features.exchanges.presentation.viewmodel.ExchangesViewModel
import com.leandrocourse.libraries.arch.koin.koinlazy.KoinLazyModuleInitializer
import com.leandrocourse.libraries.arch.navigation.extension.LocalNavController
import com.leandrocourse.libraries.arch.viewmodel.extension.observeAsEvents
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject


internal const val TOP_BAR_TEST_TAG = "TOP_BAR_TEST_TAG"
internal const val LOADING_INDICATOR_TEST_TAG = "LOADING_INDICATOR_TEST_TAG"
internal const val EMPTY_SCREE_DESCRIPTION_TEST_TAG = "EMPTY_SCREE_DESCRIPTION_TEST_TAG"
internal const val EMPTY_SCREE_ILLU_TEST_TAG = "EMPTY_SCREE_ILLU_TEST_TAG"
internal const val EMPTY_SCREE_TRY_AGAIN_TEST_TAG = "EMPTY_SCREE_TRY_AGAIN_TEST_TAG"
internal const val EXCHANGES_ITEM_TEST_TAG = "EXCHANGES_ITEM_TEST_TAG"

@Composable
internal fun ExchangesScreen(
    vm: ExchangesViewModel? = null
) {
    KoinLazyModuleInitializer(ExchangesModuleLoad)
    val viewModel = vm ?: koinViewModel()
    val state by viewModel.state.collectAsState()
    ExchangesObserve(viewModel)

    ExchangesContent(
        state = state,
        intent = viewModel::onViewIntent
    )
}

@Composable
private fun ExchangesObserve(
    viewModel: ExchangesViewModel,
    detailsNavigation: DetailsNavigation = koinInject()
) {
    val navController = LocalNavController.current

    viewModel.effect.observeAsEvents { effect ->
        when (effect) {
            is ExchangesViewEffect.NavigateToDetails -> {
                val route = DetailsRoute(effect.exchangeId)
                detailsNavigation.navigateToFeature(navController, route)
            }
        }
    }
}
