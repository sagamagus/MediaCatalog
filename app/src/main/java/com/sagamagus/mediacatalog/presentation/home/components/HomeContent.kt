package com.sagamagus.mediacatalog.presentation.home.components

import com.sagamagus.mediacatalog.domain.model.Show

data class HomeContent(
    val heroItems: List<Show>,
    val sections: List<HomeSection>
)