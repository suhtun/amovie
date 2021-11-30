package com.su.amovie.data

import android.util.Log
import com.su.amovie.data.network.ApiService
import com.su.amovie.ui.main.MovieUiModel

class MainRepository(private val apiService: ApiService) {
    suspend fun getMovies() : List<MovieUiModel> {
        val apiResponse = apiService.getPlayingMovie()
        return apiResponse.movieResults
            .map {
                MovieUiModel(
                    it.id,
                    it.poster_path,
                    it.original_title,
                    it.release_date
                )
            }
    }
}