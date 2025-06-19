package com.leandrocourse.core.data.local.model


@Entity(tableName = "exchange")
internal data class ExchangeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("exchange_id")
    val exchangeId: String,
    @ColumnInfo("website")
    val website: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("data_quote_start")
    val dataQuoteStart: String,
    @ColumnInfo("data_quote_end")
    val dataQuoteEnd: String,
    @ColumnInfo("data_orderbook_start")
    val dataOrderBookStart: String,
    @ColumnInfo("data_orderbook_end")
    val dataOrderBookEnd: String,
    @ColumnInfo("data_trade_start")
    val dataTradeStart: String,
    @ColumnInfo("data_trade_end")
    val dataTradeEnd: String,
    @ColumnInfo("data_symbols_count")
    val dataSymbolsCount: String,
    @ColumnInfo("volume_1hrs_usd")
    val volume1hrsUsd: String,
    @ColumnInfo("volume_1day_usd")
    val volume1dayUsd: String,
    @ColumnInfo("volume_1mth_usd")
    val volume1mthUsd: String,
    @ColumnInfo("icon_url")
    val iconUrl: String,
    @ColumnInfo("is_editors_choice")
    val isEditorsChoice: Boolean
)
