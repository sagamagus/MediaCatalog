package com.sagamagus.mediacatalog.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.domain.usecase.GetShowByIdUseCase
import com.sagamagus.mediacatalog.domain.util.AppResult
import com.sagamagus.mediacatalog.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getShowByIdUseCase: GetShowByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Show>>(UiState.Loading)
    val uiState: StateFlow<UiState<Show>> = _uiState.asStateFlow()

    fun loadShow(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = getShowByIdUseCase(id)) {

                is AppResult.Success -> {
                    _uiState.value = UiState.Success(result.data)
                }

                is AppResult.Error -> {
                    _uiState.value = UiState.Error(result.message)
                }
            }
        }
    }
}