package com.sagamagus.mediacatalog.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.presentation.util.toReadableDate

@Composable
fun ShowDates(show: Show) {

    val premiered = show.premiered.toReadableDate()
    val ended = show.ended.toReadableDate()

    Column {

        premiered?.let {
            Text(
                text = "Estreno: $it",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.92f),
                lineHeight = 22.sp
            )
        }

        when {
            ended != null -> {
                Text(
                    text = "Finalizó: $ended",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.92f),
                    lineHeight = 22.sp
                )
            }
            premiered != null -> {
                Text(
                    text = "En emisión",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.92f),
                    lineHeight = 22.sp
                )
            }
        }
    }
}