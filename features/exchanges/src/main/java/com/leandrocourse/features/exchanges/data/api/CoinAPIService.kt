package com.leandrocourse.features.exchanges.data.api

import com.leandrocourse.features.exchanges.data.model.ExchangeResponse
import com.leandrocourse.features.exchanges.data.model.IconResponse
import retrofit2.http.GET

internal interface CoinAPIService {

    @GET("/v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>

    @GET("/v1/exchanges/icons/64")
    suspend fun getExchangesIcons(): List<IconResponse>
}
