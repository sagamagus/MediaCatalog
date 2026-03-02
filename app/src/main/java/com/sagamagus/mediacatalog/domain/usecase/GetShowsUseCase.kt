package com.sagamagus.mediacatalog.domain.usecase

import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.domain.repository.ShowRepository
import com.sagamagus.mediacatalog.domain.util.AppResult
import javax.inject.Inject

class GetShowsUseCase @Inject constructor(
    private val repository: ShowRepository
) {

    suspend operator fun invoke(
        forceRefresh: Boolean = false
    ): AppResult<List<Show>> {
        return repository.getShows(forceRefresh)
    }
}