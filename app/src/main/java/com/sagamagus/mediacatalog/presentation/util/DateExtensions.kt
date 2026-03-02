package com.sagamagus.mediacatalog.presentation.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun String?.toReadableDate(): String? {

    if (this.isNullOrBlank()) return null

    return try {
        val date = LocalDate.parse(this)

        date.format(
            DateTimeFormatter.ofLocalizedDate(
                FormatStyle.LONG
            ).withLocale(Locale.getDefault())
        )

    } catch (e: Exception) {
        this
    }
}