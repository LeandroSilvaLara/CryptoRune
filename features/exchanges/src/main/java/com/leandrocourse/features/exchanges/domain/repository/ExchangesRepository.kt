package com.leandrocourse.features.exchanges.domain.repository

import com.leandrocourse.core.domain.model.Exchange

internal interface ExchangesRepository {
    suspend fun getExchanges(): List<Exchange>
}
