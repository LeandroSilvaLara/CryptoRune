package com.leandrocourse.features.exchanges.data.model.extension

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


private const val THOUSAND = 1_000
private const val MILLION = 1_000_000
private const val BILLION = 1_000_000_000
private const val TRILLION = 1_000_000_000_000
private const val EDITORS_CHOICE_EXCHANGE_ID = "MERCADOBITCOIN"

internal fun ExchangeResponse.toExchange(iconUrl: String?): Exchange {
    return Exchange(
        exchangeId = requireNotNull(exchangeId) { "Exchange ID cannot be null" },
        website = website.orEmpty(),
        name = name.orDash(),
        dataQuoteStart = dataQuoteStart.toFormatDate(DateFormatType.ISO_8601_TO_DD_MM_YY_HH_MM),
        dataQuoteEnd = dataQuoteEnd.toFormatDate(DateFormatType.ISO_8601_TO_DD_MM_YY_HH_MM),
        dataOrderBookStart = dataOrderBookStart.toFormatDate(DateFormatType.ISO_8601_TO_DD_MM_YY_HH_MM),
        dataOrderBookEnd = dataOrderBookEnd.toFormatDate(DateFormatType.ISO_8601_TO_DD_MM_YY_HH_MM),
        dataTradeStart = dataTradeStart.toFormatDate(DateFormatType.ISO_8601_TO_DD_MM_YY_HH_MM),
        dataTradeEnd = dataTradeEnd.toFormatDate(DateFormatType.ISO_8601_TO_DD_MM_YY_HH_MM),
        dataSymbolsCount = dataSymbolsCount.orZero().toString().orDash(),
        volume1hrsUsd = volume1hrsUsd.formatVolume(),
        volume1dayUsd = volume1dayUsd.formatVolume(),
        volume1mthUsd = volume1mthUsd.formatVolume(),
        iconUrl = iconUrl.orDash(),
        isEditorChoice = exchangeId == EDITORS_CHOICE_EXCHANGE_ID
    )
}

internal fun List<ExchangeResponse>.toExchangeList(iconResponses: List<IconResponse>): List<Exchange> {
    val iconMap = iconResponses.associateBy { it.exchangeId }

    val exchanges = map { exchangeResponse ->
        val iconUrl = iconMap[exchangeResponse.exchangeId]?.url
        exchangeResponse.toExchange(iconUrl)
    }

    return exchanges.sortedByDescending { it.exchangeId == EDITORS_CHOICE_EXCHANGE_ID }
}

private fun Double?.formatVolume(locale: Locale = Locale.getDefault()): String {
    val value = this.orZero()
    return when {
        value <= 0.0 -> "0 **"
        value >= TRILLION -> String.format(locale, "%.1fT", value / TRILLION)
        value >= BILLION -> String.format(locale, "%.1fB", value / BILLION)
        value >= MILLION -> String.format(locale, "%.1fM", value / MILLION)
        value >= THOUSAND -> String.format(locale, "%.1fK", value / THOUSAND)
        else -> this.toString()
    }
}

enum class DateFormatType(val formatMap: Pair<String, String>) {
    ISO_8601_TO_DD_MM_YY_HH_MM("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'" to "dd/MM/yy HH:mm"),
}

fun String?.toFormatDate(formatType: DateFormatType): String {
    return runCatching {
        val (input, output) = formatType.formatMap
        val inputFormat = SimpleDateFormat(input, Locale.ROOT)
            .apply { timeZone = TimeZone.getTimeZone("UTC") }
        val outputFormat = SimpleDateFormat(output, Locale.getDefault())
        val date = inputFormat.parse(this.orEmpty())
        date?.let(outputFormat::format)
    }.getOrNull().orDash()
}
