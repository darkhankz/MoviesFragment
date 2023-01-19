package com.movies.moviesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//MovieItemModel
@Entity(tableName = "movie_table")
data class MoviesDetailsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val backdrop_path: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
)