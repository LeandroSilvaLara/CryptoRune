package com.leandrocourse.core.data.local.repository

internal class LocalRepositoryImpl(
    private val dataSource: LocalDataSource,
) : MBChallengeRepository {

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
