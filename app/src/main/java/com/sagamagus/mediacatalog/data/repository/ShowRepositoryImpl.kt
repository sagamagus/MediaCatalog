package com.sagamagus.mediacatalog.data.repository

import retrofit2.HttpException
import com.sagamagus.mediacatalog.data.mapper.toDomain
import com.sagamagus.mediacatalog.data.remote.TvMazeApi
import com.sagamagus.mediacatalog.domain.model.Show
import com.sagamagus.mediacatalog.domain.repository.ShowRepository
import com.sagamagus.mediacatalog.domain.util.AppResult
import com.sagamagus.mediacatalog.domain.util.ErrorType
import kotlinx.serialization.SerializationException
import java.io.IOException
import javax.inject.Inject

class ShowRepositoryImpl @Inject constructor(
    private val api: TvMazeApi
) : ShowRepository {

    private var cachedShows: List<Show>? = null

    override suspend fun getShows(forceRefresh: Boolean): AppResult<List<Show>> {

        return try {

            if (!forceRefresh && cachedShows != null) {
                AppResult.Success(cachedShows!!)
            }

            val remoteShows = api.getShows()
                .map { it.toDomain() }

            cachedShows = remoteShows

            AppResult.Success(remoteShows)

        } catch (e: IOException) {

            // Sin internet
            if (cachedShows != null)
                AppResult.Success(cachedShows!!)
            else
                AppResult.Error(
                    message = "Sin conexión a internet",
                    type = ErrorType.NETWORK
                )

        } catch (e: HttpException) {

            AppResult.Error(
                message = "Error del servidor (${e.code()})",
                type = ErrorType.HTTP
            )

        } catch (e: SerializationException) {

            AppResult.Error(
                message = "Error procesando datos",
                type = ErrorType.SERIALIZATION
            )

        } catch (e: Exception) {

            AppResult.Error(
                message = "Error inesperado",
                type = ErrorType.UNKNOWN
            )
        }

    }

    override suspend fun getShowById(id: Int): AppResult<Show> {
        return try {

            val cached = cachedShows
                ?.firstOrNull { it.id == id }

            if (cached != null) {
                return AppResult.Success(cached)
            }

            val response = api.getShowById(id).toDomain()
            AppResult.Success(response)

        } catch (e: IOException) {

            AppResult.Error(
                message = "Sin conexión a internet",
                type = ErrorType.NETWORK
            )

        } catch (e: HttpException) {

            AppResult.Error(
                message = "Error del servidor (${e.code()})",
                type = ErrorType.HTTP
            )

        } catch (e: SerializationException) {

            AppResult.Error(
                message = "Error procesando datos",
                type = ErrorType.SERIALIZATION
            )

        } catch (e: Exception) {

            AppResult.Error(
                message = "Error inesperado",
                type = ErrorType.UNKNOWN
            )
        }
    }
}
