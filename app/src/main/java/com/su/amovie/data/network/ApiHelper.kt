package com.su.amovie.data.network

import com.su.amovie.ui.main.MovieUiModel

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() : List<MovieUiModel> = apiService.getPlayingMovie().movieResults.map { MovieUiModel(it.id,it.poster_path,it.original_title,it.release_date) }
}