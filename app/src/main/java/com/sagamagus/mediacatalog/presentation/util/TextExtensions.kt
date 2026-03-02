package com.sagamagus.mediacatalog.presentation.util

import android.text.Html

fun String.cleanHtml(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        .toString()
        .trim()
}

fun String.truncateWords(maxWords: Int): String {
    val words = this.split("\\s+".toRegex())

    return if (words.size <= maxWords) {
        this
    } else {
        words.take(maxWords).joinToString(" ") + "..."
    }
}