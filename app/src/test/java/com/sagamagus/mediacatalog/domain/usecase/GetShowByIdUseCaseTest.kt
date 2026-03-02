package com.sagamagus.mediacatalog.domain.usecase

import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.domain.repository.ShowRepository
import com.sagamagus.mediacatalog.domain.util.AppResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetShowByIdUseCaseTest {

    private val repository: ShowRepository = mockk()
    private lateinit var useCase: GetShowByIdUseCase

    @Before
    fun setup() {
        useCase = GetShowByIdUseCase(repository)
    }

    @Test
    fun `invoke should return show from repository`() = runTest {

        val show = Show(
            id = 1,
            name = "Test",
            summary = "Summary",
            imageUrl = null,
            rating = null,
            genres = emptyList(),
            premiered = null,
            ended = null,
            officialSite = null
        )

        coEvery { repository.getShowById(1) } returns AppResult.Success(show)

        val result = useCase(1)

        assertEquals(show, result)
        coVerify { repository.getShowById(1) }
    }
}