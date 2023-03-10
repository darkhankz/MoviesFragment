package com.movies.moviesapp.model.api

import com.movies.moviesapp.data.MoviesData
import com.movies.moviesapp.data.MoviesDetailsData
import com.movies.moviesapp.data.TrailersResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("3/movie/popular")
    fun getMovies (@Query("api_key") apiKey : String) : Call<MoviesData>

    @GET("3/movie/{movie_id}")
    fun getMoviesDetails(@Path("movie_id") id: Int, @Query("api_key") apiKey: String) : Call<MoviesDetailsData>

    @GET("3/movie/{movie_id}/videos")
    fun getTrailers(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Call<TrailersResponse>

    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}