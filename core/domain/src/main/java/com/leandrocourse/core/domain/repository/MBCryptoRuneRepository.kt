package com.leandrocourse.core.domain.repository

import com.leandrocourse.core.domain.model.Exchange

interface MBCryptoRuneRepository {
    suspend fun selectExchange(exchangeId: String): Exchange
    suspend fun insertAllExchanges(exchanges: List<Exchange>)
    suspend fun selectAllExchanges(): List<Exchange>
}