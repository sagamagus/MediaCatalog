package com.sagamagus.mediacatalog.domain.model

data class Show(
    val id: Int,
    val name: String,
    val summary: String,
    val imageUrl: String?,
    val rating: Double?,
    val genres: List<String>,
    val premiered: String?,
    val ended: String?,
    val officialSite: String?
)