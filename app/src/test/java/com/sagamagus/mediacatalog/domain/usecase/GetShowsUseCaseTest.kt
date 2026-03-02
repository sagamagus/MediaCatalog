package com.sagamagus.mediacatalog.domain.usecase

import android.R.attr.type
import android.R.id.message
import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.domain.repository.ShowRepository
import com.sagamagus.mediacatalog.domain.util.AppResult
import com.sagamagus.mediacatalog.domain.util.ErrorType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import net.bytebuddy.matcher.ElementMatchers.returns
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetShowsUseCaseTest {

    private val repository: ShowRepository = mockk()
    private lateinit var useCase: GetShowsUseCase

    @Before
    fun setup() {
        useCase = GetShowsUseCase(repository)
    }

    @Test
    fun `invoke should call repository with forceRefresh false by default`() = runTest {

        coEvery { repository.getShows(false) } returns AppResult.Success(emptyList())

        useCase()

        coVerify(exactly = 1) { repository.getShows(false) }
    }

    @Test
    fun `invoke should call repository with forceRefresh true`() = runTest {

        coEvery { repository.getShows(true) } returns AppResult.Success(emptyList())

        useCase(forceRefresh = true)

        coVerify(exactly = 1) { repository.getShows(true) }
    }

    @Test
    fun `invoke should return Success when repository succeeds`() = runTest {

        val fakeList = listOf(
            Show(
                id = 1,
                name = "Test",
                summary = "",
                imageUrl = null,
                rating = null,
                genres = emptyList(),
                premiered = null,
                ended = null,
                officialSite = null
            )
        )

        coEvery { repository.getShows(false) } returns AppResult.Success(fakeList)

        val result = useCase()

        assertTrue(result is AppResult.Success)
        assertEquals(fakeList, (result as AppResult.Success).data)
    }

    @Test
    fun `invoke should return Error when repository fails`() = runTest {

        coEvery { repository.getShows(false) } returns AppResult.Error(
                message = "Network error",
        type = ErrorType.NETWORK
        )

        val result = useCase()

        assertTrue(result is AppResult.Error)
        assertEquals(
            ErrorType.NETWORK,
            (result as AppResult.Error).type
        )
    }
}