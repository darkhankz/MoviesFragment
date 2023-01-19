package com.movies.moviesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.movies.moviesapp.data.MoviesDetailsData
import com.movies.moviesapp.R
import com.movies.moviesapp.view.adapters.CustomAdapter
import com.movies.moviesapp.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MoviesDetailsActivity : AppCompatActivity() {
//    private val viewModel: MoviesViewModel = MoviesViewModel()
//
//    private lateinit var titleDetails: TextView
//    private lateinit var overview: TextView
//    private lateinit var score: TextView
//    private lateinit var releaseDate: TextView
//    private lateinit var banner: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

    }

}