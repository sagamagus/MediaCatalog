package com.sagamagus.mediacatalog.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sagamagus.mediacatalog.presentation.home.components.HomeSection
import com.sagamagus.mediacatalog.presentation.home.components.HorizontalSection
import com.sagamagus.mediacatalog.presentation.state.UiState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.ui.unit.dp
import com.sagamagus.mediacatalog.presentation.home.components.ErrorContent
import com.sagamagus.mediacatalog.presentation.home.components.HeroCarousel
import com.sagamagus.mediacatalog.presentation.home.components.HomeContent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    val isRefreshing = state is UiState.Loading
    val pullState = rememberPullToRefreshState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->

        PullToRefreshBox(
            state = pullState,
            isRefreshing = isRefreshing,
            onRefresh = {
                viewModel.refresh()
            },
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            when (state) {

                is UiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("LOADING...")
                    }
                }

                is UiState.Success -> {

                    val content =
                        (state as UiState.Success<HomeContent>).data
                    val heroItems = content.heroItems
                    val sections = content.sections

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(32.dp),
                        contentPadding = PaddingValues(vertical = 24.dp)
                    ) {

                        item {
                            HeroCarousel(
                                shows = heroItems,
                                onItemClick = onItemClick
                            )
                        }

                        items(sections) { section ->

                            when (section) {

                                is HomeSection.Slider -> {
                                    HorizontalSection(
                                        title = section.title,
                                        items = section.shows,
                                        onItemClick = onItemClick
                                    )
                                }
                            }
                        }
                    }
                }

                is UiState.Error -> {
                    ErrorContent(
                        message = (state as UiState.Error).message,
                        onRetry = { viewModel.refresh() }
                    )
                }
            }
        }
    }
}