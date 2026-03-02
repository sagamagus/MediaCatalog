package com.sagamagus.mediacatalog.data.remote


import retrofit2.http.GET
import retrofit2.http.Path

interface TvMazeApi {

    @GET("shows")
    suspend fun getShows(): List<ShowDto>

    @GET("shows/{id}")
    suspend fun getShowById(@Path("id") id: Int): ShowDto

}