package com.leandrocourse.core.data.local.repository

import com.leandrocourse.core.data.local.source.LocalDataSource
import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.core.domain.repository.MBCryptoRuneRepository

internal class LocalRepositoryImpl(
    private val dataSource: LocalDataSource,
) : MBCryptoRuneRepository {

    override suspend fun selectExchange(exchangeId: String): Exchange {
        return dataSource.selectExchange(exchangeId)
    }

    override suspend fun insertAllExchanges(exchanges: List<Exchange>) {
        dataSource.insertAllExchanges(exchanges)
    }

    override suspend fun selectAllExchanges(): List<Exchange> {
        return dataSource.selectAllExchanges()
    }
}
