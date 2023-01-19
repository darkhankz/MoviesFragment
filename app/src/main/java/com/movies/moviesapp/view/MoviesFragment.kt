package com.movies.moviesapp.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.movies.moviesapp.MAIN
import com.movies.moviesapp.R
import com.movies.moviesapp.view.adapters.CustomAdapter
import com.movies.moviesapp.viewmodel.MoviesViewModel


class MoviesFragment : Fragment(), CustomAdapter.OnItemClickListener, MenuProvider {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var mMoviesRecycler: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        mMoviesRecycler = view.findViewById(R.id.recycler_view_fragment)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MoviesViewModel(requireActivity().application)
        mMoviesRecycler.layoutManager = GridLayoutManager(context, 2)
        viewModel.getMovies()
        viewModel.initDataBase()
        initObservers()
        Log.d("log", "${viewModel.getMovies()}")
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initObservers() {
        viewModel.apply {
            movies.observe(MAIN) {
                mMoviesAdapter = CustomAdapter(it, this@MoviesFragment)
                mMoviesRecycler.adapter = mMoviesAdapter
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId){
            R.id.item_favorite -> {
                MAIN.navController.navigate(R.id.action_moviesFragment_to_favoriteFragment)
                true
            }
            else -> false
        }
    }

    override fun onItemClick(movieId: Int) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)
        fragment.arguments = bundle
        MAIN.navController.navigate(R.id.action_moviesFragment_to_detailFragment, bundle)
    }

}