package com.leandrocourse.features.exchanges.data.source

import com.leandrocourse.core.data.remote.network.exception.extension.parseHttpError
import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.features.exchanges.data.api.CoinAPIService
import com.leandrocourse.features.exchanges.data.model.extension.toExchangeList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

internal class ExchangesRemoteDataSourceImpl(
    private val service: CoinAPIService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExchangesRemoteDataSource {

    override suspend fun getExchanges(): List<Exchange> {
        return runCatching {
            withContext(dispatcher) {
                val iconsResponseDeferred = async { service.getExchangesIcons() }
                val exchangesResponseDeferred = async { service.getExchanges() }

                val iconsResponse = iconsResponseDeferred.await()
                val exchangesResponse = exchangesResponseDeferred.await()

                exchangesResponse.toExchangeList(iconsResponse)
            }
        }.getOrElse { cause ->
            throw cause.parseHttpError()
        }
    }
}
