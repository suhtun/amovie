package com.su.amovie.data

import com.su.amovie.data.network.ApiService
import com.su.amovie.ui.main.MovieUiModel

class MainRepository(private val apiService: ApiService) {
    suspend fun getMovies() = apiService.getPlayingMovie().movieResults
        .map {
            MovieUiModel(
                it.id,
                it.poster_path,
                it.original_title,
                it.release_date
            )
        }
}