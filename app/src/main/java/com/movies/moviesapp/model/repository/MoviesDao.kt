package com.movies.moviesapp.model.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.movies.moviesapp.data.MoviesDetailsData

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(moviesData: MoviesDetailsData)

    @Delete
    suspend fun delete(moviesData: MoviesDetailsData)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): LiveData<List<MoviesDetailsData>>
}