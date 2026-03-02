package com.sagamagus.mediacatalog.presentation.home

import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.presentation.home.components.HomeSection
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class HomeSectionBuilderTest {

    private val builder = HomeSectionBuilder()

    @Test
    fun `buildSections groups shows by genre correctly`() {

        val shows = listOf(
            Show(
                id = 1,
                name = "A",
                summary = "",
                imageUrl = null,
                rating = 8.0,
                genres = listOf("Drama"),
                premiered = "2023-01-01",
                ended = null,
                officialSite = null
            ),
            Show(
                id = 2,
                name = "B",
                summary = "",
                imageUrl = null,
                rating = 7.0,
                genres = listOf("Drama", "Comedy"),
                premiered = "2022-01-01",
                ended = null,
                officialSite = null
            )
        )

        val sections = builder.buildSections(shows).sections

        // Verifica que exista sección Drama
        val dramaSection = sections
            .filterIsInstance<HomeSection.Slider>()
            .firstOrNull { it.title == "Drama" }

        assertNotNull(dramaSection)
        assertEquals(2, dramaSection!!.shows.size)
    }
}