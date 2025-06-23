package com.leandrocourse.features.details.presentation.viewmodel

import com.leandrocourse.core.domain.model.Exchange

internal data class DetailsViewState(
    val exchangeId: String,
    val exchange: Exchange? = null,
    val historicalData: List<Float> = emptyList()
)
