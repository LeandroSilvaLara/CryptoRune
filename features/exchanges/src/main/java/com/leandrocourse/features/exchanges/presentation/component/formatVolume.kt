package com.leandrocourse.features.exchanges.presentation.component

import java.text.DecimalFormat
import kotlin.math.ln
import kotlin.math.pow

/**
 * Formata um número (em formato de String) para uma representação compacta com sufixo (K, M, B).
 * Ex: "23300000.5" -> "23,3M"
 * Ex: "2000000000" -> "2,0B"
 */
private const val DEFAULT_VALUE = "-"
private const val THOUSAND = 1000.0
private val SUFFIXES = charArrayOf('K', 'M', 'B')
private val DECIMAL_FORMAT = DecimalFormat("0.##")
private val SUFFIX_FORMAT = "%.1f%c"

fun formatVolume(value: String?): String {
    if (value.isNullOrBlank()) return DEFAULT_VALUE

    return try {
        val number = value.toDouble()
        if (number < THOUSAND) return DECIMAL_FORMAT.format(number)

        val exp = (ln(number) / ln(THOUSAND)).toInt()
        String.format(SUFFIX_FORMAT, number / THOUSAND.pow(exp.toDouble()), SUFFIXES[exp - 1])
    } catch (e: NumberFormatException) {
        value
    }
}