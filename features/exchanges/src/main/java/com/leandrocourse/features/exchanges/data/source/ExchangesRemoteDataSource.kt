package com.leandrocourse.features.exchanges.data.source


internal interface ExchangesRemoteDataSource {
    suspend fun getExchanges(): List<Exchange>
}