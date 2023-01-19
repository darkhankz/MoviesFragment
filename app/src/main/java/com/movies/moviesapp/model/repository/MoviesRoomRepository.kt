package com.movies.moviesapp.model.repository

import androidx.lifecycle.LiveData
import com.movies.moviesapp.data.MoviesDetailsData

//moviesRepository
interface MoviesRoomRepository {
    val allMovies: LiveData<List<MoviesDetailsData>>
    suspend fun insertMovie(moviesData: MoviesDetailsData, onSuccess:() -> Unit)
    suspend fun deleteMovie(moviesData: MoviesDetailsData, onSuccess:() -> Unit)

}