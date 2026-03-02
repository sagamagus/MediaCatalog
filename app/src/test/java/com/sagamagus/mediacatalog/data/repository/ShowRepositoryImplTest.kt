package com.sagamagus.mediacatalog.data.repository

import com.sagamagus.mediacatalog.data.remote.ShowDto
import com.sagamagus.mediacatalog.data.remote.TvMazeApi
import com.sagamagus.mediacatalog.domain.util.AppResult
import com.sagamagus.mediacatalog.domain.util.ErrorType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import io.mockk.mockk
import io.mockk.coEvery
import io.mockk.coVerify
import junit.framework.TestCase.assertTrue
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


@OptIn(ExperimentalCoroutinesApi::class)
class ShowRepositoryImplTest {

    private val api: TvMazeApi = mockk()
    private lateinit var repository: ShowRepositoryImpl

    @Before
    fun setup() {
        repository = ShowRepositoryImpl(api)

    }

    @Test
    fun `should return cached data when network fails`() = runTest {

        val fakeDtos = listOf(
            ShowDto(1, "Test", emptyList(), null, null, null, null, null, null)
        )

        coEvery { api.getShows() } returns fakeDtos

        repository.getShows(forceRefresh = false)

        coEvery { api.getShows() } throws IOException()

        val result = repository.getShows(forceRefresh = true)

        assertTrue(result is AppResult.Success)
    }

    @Test
    fun `getShows returns success when api works`() = runTest {

        val fakeDtos = listOf(
            ShowDto(1, "Test", emptyList(), null, null, null, null, null, null)
        )

        coEvery { api.getShows() } returns fakeDtos

        val result = repository.getShows(false)

        assertTrue(result is AppResult.Success)

        val data = (result as AppResult.Success).data
        assertEquals(fakeDtos.size, data.size)
    }

    @Test
    fun `getShows returns http error when api fails`() = runTest {

        val errorResponse = Response.error<List<ShowDto>>(
            500,
            "Server error".toResponseBody(null)
        )

        coEvery { api.getShows() } throws HttpException(errorResponse)

        val result = repository.getShows(true)

        assertTrue(result is AppResult.Error)
        assertEquals(ErrorType.HTTP, (result as AppResult.Error).type)
    }

    @Test
    fun `getShows returns network error when no internet`() = runTest {

        coEvery { api.getShows() } throws IOException()

        val result = repository.getShows(true)

        assertTrue(result is AppResult.Error)
        assertEquals(ErrorType.NETWORK, (result as AppResult.Error).type)
    }
}