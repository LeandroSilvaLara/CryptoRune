package com.leandrocourse.features.exchanges.data.repository

/**
 * Repository implementation that fetches exchange data from the remote data
 * source. It acts as the bridge between the domain layer and network layer for
 * the Exchanges feature.
 */

import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.features.exchanges.data.source.ExchangesRemoteDataSource
import com.leandrocourse.features.exchanges.domain.repository.ExchangesRepository

internal class ExchangesRepositoryImpl(
    private val dataSource: ExchangesRemoteDataSource
) : ExchangesRepository {

    override suspend fun getExchanges(): List<Exchange> {
        return dataSource.getExchanges()
    }
}
