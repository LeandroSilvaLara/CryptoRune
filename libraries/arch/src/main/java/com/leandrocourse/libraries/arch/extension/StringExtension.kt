package com.leandrocourse.libraries.arch.extension

fun emptyString() = ""

fun blankString() = " "

fun String?.orDash(): String = this ?: "---"
