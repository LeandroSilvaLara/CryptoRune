package com.leandrocourse.features.exchanges.presentation.viewmodel

internal sealed interface ExchangesViewIntent {
    data object OnGetExchanges : ExchangesViewIntent
    data object OnCLoseErrorModal : ExchangesViewIntent
    data class OnExchangeClicked(val exchangeId: String) : ExchangesViewIntent
}