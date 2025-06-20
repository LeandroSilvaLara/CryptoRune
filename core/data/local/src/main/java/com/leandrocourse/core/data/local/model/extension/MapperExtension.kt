package com.leandrocourse.core.data.local.model.extension

import com.leandrocourse.core.data.local.model.ExchangeEntity
import com.leandrocourse.core.domain.model.Exchange


internal fun ExchangeEntity.toExchange(): Exchange {
    return Exchange(
        exchangeId = exchangeId,
        website = website,
        name = name,
        dataQuoteStart = dataQuoteStart,
        dataQuoteEnd = dataQuoteEnd,
        dataOrderBookStart = dataOrderBookStart,
        dataOrderBookEnd = dataOrderBookEnd,
        dataTradeStart = dataTradeStart,
        dataTradeEnd = dataTradeEnd,
        dataSymbolsCount = dataSymbolsCount,
        volume1hrsUsd = volume1hrsUsd,
        volume1dayUsd = volume1dayUsd,
        volume1mthUsd = volume1mthUsd,
        iconUrl = iconUrl,
        isEditorChoice = isEditorsChoice
    )
}

internal fun Exchange.toExchangeEntity(): ExchangeEntity {
    return ExchangeEntity(
        exchangeId = exchangeId,
        website = website,
        name = name,
        dataQuoteStart = dataQuoteStart,
        dataQuoteEnd = dataQuoteEnd,
        dataOrderBookStart = dataOrderBookStart,
        dataOrderBookEnd = dataOrderBookEnd,
        dataTradeStart = dataTradeStart,
        dataTradeEnd = dataTradeEnd,
        dataSymbolsCount = dataSymbolsCount,
        volume1hrsUsd = volume1hrsUsd,
        volume1dayUsd = volume1dayUsd,
        volume1mthUsd = volume1mthUsd,
        iconUrl = iconUrl,
        isEditorsChoice = isEditorChoice
    )
}

internal fun List<ExchangeEntity>.toExchangeList(): List<Exchange> {
    return map { it.toExchange() }
}

internal fun List<Exchange>.toExchangeListEntity(): List<ExchangeEntity> {
    return map { it.toExchangeEntity() }
}
