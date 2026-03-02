package com.sagamagus.mediacatalog.presentation.home

import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.presentation.home.components.HomeContent
import com.sagamagus.mediacatalog.presentation.home.components.HomeSection

class HomeSectionBuilder {

    fun buildSections(shows: List<Show>): HomeContent {

        val popular = shows
            .sortedByDescending { it.rating ?: 0.0 }
            .take(10)

        val news = shows
            .sortedByDescending { it.premiered ?: "" }
            .take(10)

        val heroItems = popular.take(5)

        val showsByGenre = shows
            .flatMap { show ->
                show.genres.map { genre -> genre to show }
            }
            .groupBy(
                keySelector = { it.first },
                valueTransform = { it.second }
            )

        val genreSections = showsByGenre
            .map { (genre, genreShows) ->
                HomeSection.Slider(
                    title = genre,
                    shows = genreShows.take(10)
                )
            }
            .sortedByDescending { it.shows.size }
            .take(5)

        val sections = buildList {
            add(HomeSection.Slider("Popular", popular))
            add(HomeSection.Slider("New", news))
            addAll(genreSections)
        }

        return HomeContent(
            heroItems = heroItems,
            sections = sections
        )
    }
}