package com.movies.moviesapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.movies.moviesapp.data.*
import com.movies.moviesapp.model.repository.MoviesDBRepository
import com.movies.moviesapp.model.repository.MoviesDBRepositoryImpl
import com.movies.moviesapp.model.repository.MoviesRoomImpl
import com.movies.moviesapp.model.room.MoviesRoomDatabase
import com.movies.moviesapp.moviesRoomImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val mMoviesRepository : MoviesDBRepository = MoviesDBRepositoryImpl()
    private val _movies = MutableLiveData<List<MoviesDetailsData>>()
    val movies : LiveData<List<MoviesDetailsData>> = _movies

    private val _moviesDetails = MutableLiveData<MoviesDetailsData>()
    val moviesDetails : LiveData<MoviesDetailsData> = _moviesDetails

    val movieTrailers = MutableLiveData<List<TrailersResult>>()

    val context = application

    fun getMovies(){
        mMoviesRepository.getMovies().enqueue(object : Callback<MoviesData> {
            override fun onResponse(call: Call<MoviesData>, response: Response<MoviesData>) {
            _movies.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<MoviesData>, t: Throwable) {
            }

        })
    }

    fun getMoviesDetails(movieId: Int) {
        val response = mMoviesRepository.getMoviesDetails(movieId)
        response.enqueue(object : Callback<MoviesDetailsData> {
            override fun onResponse(
                call: Call<MoviesDetailsData>,
                response: Response<MoviesDetailsData>
            ) {
                    _moviesDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<MoviesDetailsData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

     fun fetchTrailers(movieId: Int) {
        val response = mMoviesRepository.fetchTrailers(movieId)
        response.enqueue(object : Callback<TrailersResponse>{
            override fun onResponse(
                call: Call<TrailersResponse>,
                response: Response<TrailersResponse>
            ) {
                if (response.isSuccessful) {
                    movieTrailers.postValue(response.body()?.results)
                    Log.d("viewModel", "trails ")

                }
            }

            override fun onFailure(call: Call<TrailersResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

    fun initDataBase(){
        val daoMovies = MoviesRoomDatabase.getInstance(context).getMoviesDao()
        moviesRoomImpl = MoviesRoomImpl(daoMovies)
    }

    fun getAllMovies(): LiveData<List<MoviesDetailsData>>{
        return moviesRoomImpl.allMovies
    }

    fun insert(moviesData: MoviesDetailsData, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            moviesRoomImpl.insertMovie(moviesData){
                onSuccess()
            }
        }

    fun delete(moviesData: MoviesDetailsData, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            moviesRoomImpl.deleteMovie(moviesData){
                onSuccess()
            }
        }

}