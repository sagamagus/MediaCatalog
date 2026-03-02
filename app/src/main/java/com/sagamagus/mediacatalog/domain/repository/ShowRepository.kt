package com.sagamagus.mediacatalog.domain.repository

import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.domain.util.AppResult

interface ShowRepository {
    suspend fun getShows(forceRefresh: Boolean = false): AppResult<List<Show>>
    suspend fun getShowById(id: Int): AppResult<Show>
}