package com.sagamagus.mediacatalog.presentation.detail

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.presentation.detail.components.DetailContent
import com.sagamagus.mediacatalog.presentation.state.UiState

@Composable
fun DetailScreen(
    id: Int,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadShow(id)
    }

    when (state) {

        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error loading detail")
            }
        }

        is UiState.Success -> {
            val show = (state as UiState.Success<Show>).data

            DetailContent(show,
                onPlayTrailer = { trailerUrl ->
                    navController.navigate(
                        "trailer?url=${Uri.encode(trailerUrl)}"
                    )
                })
        }
    }
}