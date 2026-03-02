package com.sagamagus.mediacatalog.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagamagus.mediacatalog.domain.usecase.GetShowsUseCase
import com.sagamagus.mediacatalog.domain.util.AppResult
import com.sagamagus.mediacatalog.presentation.home.components.HomeSection
import com.sagamagus.mediacatalog.presentation.state.UiState
import com.sagamagus.mediacatalog.presentation.home.HomeSectionBuilder
import com.sagamagus.mediacatalog.presentation.home.components.HomeContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getShowsUseCase: GetShowsUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<HomeContent>>(UiState.Loading)

    val uiState: StateFlow<UiState<HomeContent>> =
        _uiState.asStateFlow()

    init {
        loadShows()
    }

    fun refresh() {
        loadShows(true)
    }

    fun loadShows(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = getShowsUseCase(forceRefresh)) {

                is AppResult.Success -> {
                    val sectionBuilder = HomeSectionBuilder()
                    val content = sectionBuilder.buildSections(result.data)
                    _uiState.value = UiState.Success(content)
                }

                is AppResult.Error -> {
                    _uiState.value = UiState.Error(result.message)
                }
            }
        }
    }
}