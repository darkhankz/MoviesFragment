package com.movies.moviesapp.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.movies.moviesapp.data.MoviesDetailsData
import com.movies.moviesapp.model.repository.MoviesDao

@Database(entities = [MoviesDetailsData::class], version = 2)
abstract class MoviesRoomDatabase: RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object{
        private var database: MoviesRoomDatabase ?= null

        fun getInstance(context: Context): MoviesRoomDatabase{
            return if (database == null){
                database = Room
                    .databaseBuilder(context, MoviesRoomDatabase::class.java, "db")
                    .build()
                database as MoviesRoomDatabase
            } else{
                database as MoviesRoomDatabase

            }
        }
    }
}