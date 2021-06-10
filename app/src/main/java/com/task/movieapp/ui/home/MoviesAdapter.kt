package com.task.movieapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.task.movieapp.R
import com.task.movieapp.databinding.ListItemMovieBinding
import com.task.movieapp.domain.movie.model.Movie
import com.task.movieapp.utils.invisible
import com.task.movieapp.utils.setOnDebouncedClickListener

class MoviesAdapter(
    private val onMovieClick: (Int) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.Holder>() {

    private var movies: ArrayList<Movie> = arrayListOf()

    fun addMovies(list: List<Movie>) {
        val lastPos = movies.size - 1
        this.movies.addAll(list)
        notifyItemInserted(lastPos + 1)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ListItemMovieBinding.bind(itemView)

        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            movie.rating?.let { binding.tvRating.text = it.toString() }
                ?: binding.tvRating.invisible()

            movie.releaseDate?.let { binding.tvReleaseDate.text = it.take(4) }
                ?: binding.tvReleaseDate.invisible()

            Picasso.get()
                .load(movie.posterPath)
                .into(binding.ivPoster)

            binding.root.setOnDebouncedClickListener {
                onMovieClick.invoke(movies[layoutPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = this.movies.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movies[position])
    }
}