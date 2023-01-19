package com.movies.moviesapp.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.movies.moviesapp.MAIN
import com.movies.moviesapp.R
import com.movies.moviesapp.data.MoviesDetailsData
import com.movies.moviesapp.view.DetailFragment
import com.squareup.picasso.Picasso


class CustomAdapter(private val mList: List<MoviesDetailsData>?, private val listener: OnItemClickListener?) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val itemsViewModel = mList?.get(position)

        Picasso.get().load("https://image.tmdb.org/t/p/w500/${itemsViewModel?.poster_path}").into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel?.title

        holder.itemView.setOnClickListener {
            val movieId = mList?.get(position)?.id
            if (movieId != null) {
                listener?.onItemClick(movieId)
            }
        }

//        holder.itemView.setOnClickListener {
//            val movieId = mList?.get(position)?.id
//            val fragment = DetailFragment()
//            val bundle = Bundle()
//
//            if (movieId != null) {
//                bundle.putInt("movie_id", movieId )
//            }
//            fragment.arguments = bundle
//            MAIN.navController.navigate(R.id.action_moviesFragment_to_detailFragment, bundle)
//        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList!!.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textview)
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
    }

    interface OnItemClickListener {
        fun onItemClick(movieId: Int)
    }

}

