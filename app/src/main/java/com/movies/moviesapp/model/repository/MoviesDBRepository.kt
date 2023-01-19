package com.movies.moviesapp.model.repository

import com.movies.moviesapp.data.MoviesData
import com.movies.moviesapp.data.MoviesDetailsData
import com.movies.moviesapp.data.TrailersResponse
import retrofit2.Call

interface MoviesDBRepository {
    fun getMovies(): Call<MoviesData>
    fun getMoviesDetails(movieId: Int): Call<MoviesDetailsData>
    fun fetchTrailers(movieId: Int): Call<TrailersResponse>
}