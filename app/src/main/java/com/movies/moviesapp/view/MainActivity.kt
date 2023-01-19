package com.movies.moviesapp.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.movies.moviesapp.MAIN
import com.movies.moviesapp.R

class MainActivity : AppCompatActivity(){
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MAIN = this
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host)

    }


}

