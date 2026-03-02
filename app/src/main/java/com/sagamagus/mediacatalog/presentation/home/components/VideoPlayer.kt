package com.sagamagus.mediacatalog.presentation.home.components




import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.common.MediaItem


@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Crea el jugador
    val player = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                setMediaItem(MediaItem.fromUri(videoUrl))
                prepare()
                playWhenReady = true
            }
    }

    DisposableEffect(
        key1 = player
    ) {
        onDispose {
            player.release()
        }
    }

    // Componente UI
    AndroidView(
        factory = {
            PlayerView(context).apply {
                this.player = player
                useController = true
            }
        },
        modifier = modifier
    )
}