package com.sagamagus.mediacatalog.data.mapper

import com.sagamagus.mediacatalog.data.remote.ShowDto
import com.sagamagus.mediacatalog.domain.model.Show

fun ShowDto.toDomain(): Show {
    return Show(
        id = id,
        name = name,
        summary = summary.orEmpty(),
        imageUrl = image?.medium,
        rating = rating?.average,
        genres = genres,
        premiered = premiered,
        ended = ended,
        officialSite = officialSite

    )
}