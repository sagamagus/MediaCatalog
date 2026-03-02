package com.sagamagus.mediacatalog.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ShowDto(
    val id: Int,
    val name: String,
    val genres: List<String> = emptyList(),
    val rating: RatingDto? = null,
    val image: ImageDto? = null,
    val summary: String? = null,
    val premiered: String? = null,
    val ended: String? = null,
    val officialSite: String? = null
)

@Serializable
data class RatingDto(
    val average: Double? = null
)

@Serializable
data class ImageDto(
    val medium: String? = null
)