package com.movies.moviesapp.model.repository

import com.movies.moviesapp.data.MoviesData
import com.movies.moviesapp.data.MoviesDetailsData
import com.movies.moviesapp.data.TrailersResponse
import com.movies.moviesapp.model.api.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl: MoviesDBRepository {
    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<MoviesData> {
        return apiInterface.getMovies("6e76ecffda0a59dc4f19a343c6e7648a")
    }

    override fun getMoviesDetails(movieId: Int): Call<MoviesDetailsData> {
        return apiInterface.getMoviesDetails(movieId, "6e76ecffda0a59dc4f19a343c6e7648a")
    }

    override fun fetchTrailers(movieId: Int): Call<TrailersResponse> {
        return apiInterface.getTrailers(movieId, "6e76ecffda0a59dc4f19a343c6e7648a")

    }
}
