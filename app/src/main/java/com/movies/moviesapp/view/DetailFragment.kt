package com.movies.moviesapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movies.moviesapp.MAIN
import com.movies.moviesapp.R
import com.movies.moviesapp.data.MoviesDetailsData
import com.movies.moviesapp.model.repository.SaveShared
import com.movies.moviesapp.view.adapters.TrailersAdapter
import com.movies.moviesapp.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel
    private var isFavorite = false

    private lateinit var titleDetails: TextView
    private lateinit var overview: TextView
    private lateinit var score: TextView
    private lateinit var releaseDate: TextView
    private lateinit var banner: ImageView
    private lateinit var favoriteClick: ImageView
    private lateinit var trailersRecyclerView: RecyclerView
    private lateinit var trailersAdapter: TrailersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        init(view)
        trailersRecyclerView = view.findViewById(R.id.trailers_recycler_view)

        favoriteClick = view.findViewById(R.id.img_detail_favorite)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MoviesViewModel(requireActivity().application)

        trailersRecyclerView.layoutManager = LinearLayoutManager(context)
        val movieId = arguments?.getInt("movie_id")

        if (movieId != null) {
            viewModel.getMoviesDetails(movieId)
            initObservers()
            Log.d("MovieDetailsActivity", "Movie ID: $movieId")

        }

        if (movieId != null) {
            viewModel.fetchTrailers(movieId)
            initObserversTrailers()
            Log.d("MovieDetailsActivity", "Movie trailer: ${viewModel.movieTrailers}")

        }


    }

    private fun initObserversTrailers() {
        viewModel.apply {
            movieTrailers.observe(MAIN){
                trailersAdapter = TrailersAdapter(it)
                trailersRecyclerView.adapter = trailersAdapter

            } }
    }


    private fun init(view: View) {
        titleDetails = view.findViewById(R.id.movies_details_title)
        overview = view.findViewById(R.id.movies_details_body_overview)
        score = view.findViewById(R.id.movies_details_score)
        releaseDate = view.findViewById(R.id.movies_details_date)
        banner = view.findViewById(R.id.movies_details_image_banner)
    }

    private fun initObservers() {
        viewModel.apply {
            moviesDetails.observe(MAIN) {
                setMovieInformation(it)
            }

        }
    }


    private fun setMovieInformation(movieDetails: MoviesDetailsData){
        val valueBoolean = SaveShared.getFavorite(MAIN, movieDetails.id.toString())
        if (isFavorite != valueBoolean){
            favoriteClick.setImageResource(R.drawable.ic_baseline_favorite_24)

        } else{
            favoriteClick.setImageResource(R.drawable.ic_baseline_favorite_border_24)

        }

        titleDetails.text = movieDetails.title
        overview.text = movieDetails.overview
        releaseDate.text = movieDetails.release_date
        score.text = movieDetails.vote_average.toString()
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movieDetails.backdrop_path).into(banner)

        favoriteClick.setOnClickListener{
            isFavorite = if(isFavorite == valueBoolean) {
                favoriteClick.setImageResource(R.drawable.ic_baseline_favorite_24)
                SaveShared.setFavorite(MAIN, movieDetails.id.toString(), true )
                viewModel.insert(movieDetails){}
                true
            } else {
                favoriteClick.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                SaveShared.setFavorite(MAIN, movieDetails.id.toString(), false )
                viewModel.delete(movieDetails){}

                false

            }
        }
    }

}