package com.leandrocourse.features.details.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.leandrocourse.core.domain.usecase.SelectExchangeUseCase
import com.leandrocourse.libraries.arch.viewmodel.ViewIntent
import com.leandrocourse.libraries.arch.viewmodel.ViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random


internal class DetailsViewModel(
    exchangeId: String,
    private val selectExchangeUseCase: SelectExchangeUseCase,
) : ViewModel<DetailsViewState, DetailsViewEffect>(DetailsViewState(exchangeId)),
    ViewIntent<DetailsViewIntent> {

    override fun onViewIntent(intent: DetailsViewIntent) {
        when (intent) {
            DetailsViewIntent.OnInitView -> handleSelectExchange()
            DetailsViewIntent.OnBackClicked -> handleOnBackClicked()
            is DetailsViewIntent.OnNavigateToWebsite -> handleOnNavigateToWebsite(intent.url)
        }
    }

    private fun handleSelectExchange() {
        viewModelScope.launch {
            runCatching {
                val exchange = selectExchangeUseCase(viewState.exchangeId)
                onState { copy(exchange = exchange, historicalData = emptyList()) }
                val fullChartData = (1..30).map { Random.nextInt(100, 500).toFloat() }
                fullChartData.forEachIndexed { index, _ ->
                    kotlinx.coroutines.delay(50)
                    onState { copy(historicalData = fullChartData.subList(0, index + 1)) }
                }

            }.onFailure {
            }
        }
    }

    private fun handleOnBackClicked() {
        onEffect { DetailsViewEffect.NavigateToExchanges }
    }

    private fun handleOnNavigateToWebsite(url: String) {
        onEffect { DetailsViewEffect.NavigateToWebsite(url) }
    }
}
