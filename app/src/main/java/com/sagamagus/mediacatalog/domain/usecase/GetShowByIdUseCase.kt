package com.sagamagus.mediacatalog.domain.usecase

import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.domain.repository.ShowRepository
import com.sagamagus.mediacatalog.domain.util.AppResult
import javax.inject.Inject

class GetShowByIdUseCase @Inject constructor(
    private val repository: ShowRepository
) {
    suspend operator fun invoke(id: Int): AppResult<Show> {
        return repository.getShowById(id)
    }
}