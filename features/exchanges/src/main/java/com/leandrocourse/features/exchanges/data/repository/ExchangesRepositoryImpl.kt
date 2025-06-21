package com.leandrocourse.features.exchanges.data.repository

internal class ExchangesRepositoryImpl(
    private val dataSource: ExchangesRemoteDataSource
) : ExchangesRepository {

    override suspend fun getExchanges(): List<Exchange> {
        return dataSource.getExchanges()
    }
}
