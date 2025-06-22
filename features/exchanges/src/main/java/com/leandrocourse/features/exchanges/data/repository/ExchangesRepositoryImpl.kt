package com.leandrocourse.features.exchanges.data.repository

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
