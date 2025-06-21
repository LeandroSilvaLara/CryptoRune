package com.leandrocourse.features.exchanges.data.model

import com.google.gson.annotations.SerializedName

internal data class ExchangeResponse(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("data_quote_start") val dataQuoteStart: String?,
    @SerializedName("data_quote_end") val dataQuoteEnd: String?,
    @SerializedName("data_orderbook_start") val dataOrderBookStart: String?,
    @SerializedName("data_orderbook_end") val dataOrderBookEnd: String?,
    @SerializedName("data_trade_start") val dataTradeStart: String?,
    @SerializedName("data_trade_end") val dataTradeEnd: String?,
    @SerializedName("data_symbols_count") val dataSymbolsCount: Int?,
    @SerializedName("volume_1hrs_usd") val volume1hrsUsd: Double?,
    @SerializedName("volume_1day_usd") val volume1dayUsd: Double?,
    @SerializedName("volume_1mth_usd") val volume1mthUsd: Double?
)
