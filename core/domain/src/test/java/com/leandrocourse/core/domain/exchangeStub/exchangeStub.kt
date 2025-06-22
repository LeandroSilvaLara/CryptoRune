package com.leandrocourse.core.domain.exchangeStub

import com.leandrocourse.core.domain.model.Exchange

internal fun exchangeStub() = Exchange(
    exchangeId = "MERCADOBITCOIN",
    website = "https://www.mercadobitcoin.com.br/",
    name = "Mercado Bitcoin",
    dataQuoteStart = "15/03/17 00:00",
    dataQuoteEnd = "24/08/24 00:00",
    dataOrderBookStart = "14/03/17 20:05",
    dataOrderBookEnd = "06/07/23 00:00",
    dataTradeStart = "07/03/17 00:00",
    dataTradeEnd = "24/08/24 00:00",
    dataSymbolsCount = "462",
    volume1hrsUsd = "228.67",
    volume1dayUsd = "829.7K",
    volume1mthUsd = "192.7M",
    iconUrl = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/5fbfbd742fb64c67a3963ebd7265f9f3.png",
    isEditorChoice = true
)

internal fun exchangesStub() = listOf(
    exchangeStub()
)
