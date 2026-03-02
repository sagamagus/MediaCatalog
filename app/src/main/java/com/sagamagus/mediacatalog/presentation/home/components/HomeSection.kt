package com.sagamagus.mediacatalog.presentation.home.components

import com.sagamagus.mediacatalog.domain.model.Show

sealed class HomeSection {

    data class Slider(
        val title: String,
        val shows: List<Show>
    ) : HomeSection()
}