package com.leandrocourse.core.data.local.source

interface LocalDataSource {
    suspend fun selectExchange(exchangeId: String): Exchange
    suspend fun insertAllExchanges(exchanges: List<Exchange>)
    suspend fun selectAllExchanges(): List<Exchange>
}