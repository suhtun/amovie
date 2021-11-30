package com.su.amovie.data.network

import retrofit2.http.GET

interface ApiService {
    @GET("now_playing?page=1")
    suspend fun getPlayingMovie(): PlayingMoviewsResponse
}