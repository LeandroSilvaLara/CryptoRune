package com.leandrocourse.features.exchanges.presentation.viewmodel

import com.leandrocourse.core.data.remote.model.ErrorType
import com.leandrocourse.core.domain.model.Exchange

internal data class ExchangesViewState(
    val shouldShowLoading: Boolean = false,
    val shouldShowError: Boolean = false,
    val exchanges: List<Exchange> = emptyList(),
    val errorType: ErrorType = ErrorType.Generic
)