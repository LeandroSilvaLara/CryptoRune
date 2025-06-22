package com.leandrocourse.features.exchanges.presentation.viewmodel

import com.leandrocourse.core.data.remote.model.ErrorType
import com.leandrocourse.core.domain.model.Exchange

internal data class ExchangesViewState(
    val shouldShowLoading: Boolean = false,
    val shouldShowError: Boolean = false,
    val exchanges: List<Exchange> = emptyList(),
    val errorType: ErrorType = ErrorType.Generic,

    val isLoading: Boolean = false,
    val portfolioValue: Double = 189543.00,
    val portfolioDisplayValue: String = "$189,543.00",
    val portfolioChangePercent: Double = 21.54,
    val selectedCurrency: String = "USD",
    val availableCurrencies: List<String> = listOf("USD", "BRL", "EUR", "JPY"),
    val conversionRates: Map<String, Double>? = null
)

