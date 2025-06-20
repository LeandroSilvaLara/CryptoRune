package com.leandrocourse.features.details.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.leandrocourse.core.domain.usecase.SelectExchangeUseCase
import com.leandrocourse.libraries.arch.viewmodel.ViewIntent
import com.leandrocourse.libraries.arch.viewmodel.ViewModel
import kotlinx.coroutines.launch


internal class DetailsViewModel(
    exchangeId: String,
    private val selectExchangeUseCase: SelectExchangeUseCase
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
            runCatching { selectExchangeUseCase(viewState.exchangeId) }
                .onSuccess { onState { copy(exchange = it) } }
        }
    }

    private fun handleOnBackClicked() {
        onEffect { DetailsViewEffect.NavigateToExchanges }
    }

    private fun handleOnNavigateToWebsite(url: String) {
        onEffect { DetailsViewEffect.NavigateToWebsite(url) }
    }
}
