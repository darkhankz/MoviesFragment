package com.movies.moviesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movies.moviesapp.MAIN
import com.movies.moviesapp.R
import com.movies.moviesapp.view.adapters.CustomAdapter
import com.movies.moviesapp.viewmodel.MoviesViewModel


class FavoriteFragment : Fragment(), CustomAdapter.OnItemClickListener {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var mMoviesRecycler: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        mMoviesRecycler = view.findViewById(R.id.recycler_view_fragment_favorite)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MoviesViewModel(requireActivity().application)
        mMoviesRecycler.layoutManager = GridLayoutManager(context, 2)
        initObservers()
    }

    private fun initObservers() {
        viewModel.apply {
            getAllMovies().observe(MAIN) {
                mMoviesAdapter = CustomAdapter(it.asReversed(), this@FavoriteFragment)
                mMoviesRecycler.adapter = mMoviesAdapter
            }
        }
    }

    override fun onItemClick(movieId: Int) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)
        fragment.arguments = bundle
        MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
    }
}