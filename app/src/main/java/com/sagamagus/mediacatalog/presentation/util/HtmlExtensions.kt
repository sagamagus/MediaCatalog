package com.sagamagus.mediacatalog.presentation.util

import android.graphics.Typeface
import android.text.Html
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

fun String.toAnnotatedString(): AnnotatedString {
    val spanned = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)

    return buildAnnotatedString {
        append(spanned.toString())

        // Bold & Italic
        spanned.getSpans(0, spanned.length, StyleSpan::class.java)
            .forEach { span ->

                val start = spanned.getSpanStart(span)
                val end = spanned.getSpanEnd(span)

                when (span.style) {
                    Typeface.BOLD -> addStyle(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start,
                        end
                    )

                    Typeface.ITALIC -> addStyle(
                        SpanStyle(fontStyle = FontStyle.Italic),
                        start,
                        end
                    )

                    Typeface.BOLD_ITALIC -> addStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        ),
                        start,
                        end
                    )
                }
            }

        // Underline
        spanned.getSpans(0, spanned.length, UnderlineSpan::class.java)
            .forEach { span ->
                val start = spanned.getSpanStart(span)
                val end = spanned.getSpanEnd(span)

                addStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline),
                    start,
                    end
                )
            }
    }
}