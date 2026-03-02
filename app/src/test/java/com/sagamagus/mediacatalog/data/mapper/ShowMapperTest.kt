package com.sagamagus.mediacatalog.data.mapper

import com.sagamagus.mediacatalog.data.remote.ImageDto
import com.sagamagus.mediacatalog.data.remote.RatingDto
import com.sagamagus.mediacatalog.data.remote.ShowDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShowMapperTest {

    @Test
    fun `toDomain should map dto correctly`() {

        val dto = ShowDto(
            id = 1,
            name = "Test Show",
            genres = listOf("Drama"),
            rating = RatingDto(8.5),
            image = ImageDto("image_url"),
            summary = "<b>Test</b> Summary",
            premiered = "2023-01-01",
            ended = null,
            officialSite = "https://test.com"
        )

        val result = dto.toDomain()

        assertEquals(1, result.id)
        assertEquals("Test Show", result.name)
        assertEquals(8.5, result.rating)
        assertEquals("image_url", result.imageUrl)
        assertEquals("2023-01-01", result.premiered)
        assertEquals("https://test.com", result.officialSite)
    }
}