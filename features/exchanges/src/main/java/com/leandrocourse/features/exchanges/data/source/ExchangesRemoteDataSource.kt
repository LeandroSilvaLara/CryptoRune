package com.leandrocourse.features.exchanges.data.source

import com.leandrocourse.core.domain.model.Exchange


internal interface ExchangesRemoteDataSource {
    suspend fun getExchanges(): List<Exchange>
}