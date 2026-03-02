package com.sagamagus.mediacatalog.presentation.detail.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.presentation.util.toAnnotatedString
import androidx.core.net.toUri
import androidx.navigation.NavController

@Composable
fun DetailContent(
    show: Show,
    onPlayTrailer: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {

        // 🔥 Poster con overlay degradado
        item {
            Box {

                AsyncImage(
                    model = show.imageUrl,
                    contentDescription = show.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(340.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                )
                            )
                        )
                )
            }
        }

        // 🔥 Contenido principal con márgenes elegantes
        item {

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                Text(
                    text = show.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                ShowRating(show.rating)

                GenreChips(show.genres)

                ShowDates(show)

                Text(
                    text = show.summary.toAnnotatedString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.92f),
                    lineHeight = 22.sp
                )
                val context = LocalContext.current
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    show.officialSite?.let { site ->
                        Button(
                            onClick = {
                                val safeUrl = if (site.startsWith("http")) site else "https://$site"
                                context.startActivity(
                                    Intent(Intent.ACTION_VIEW, safeUrl.toUri())
                                )
                            }
                        ) {
                            Text("Visit official site")
                        }
                    }

                    Button(
                        onClick = {
                            val trailerUrl =
                                "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"

                            onPlayTrailer(trailerUrl)
                        }
                    ) {
                        Text("Ver Trailer")
                    }
                }
            }
        }
    }
}