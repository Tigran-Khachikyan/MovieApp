package com.task.movieapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

    private var movies: List<Movie> = arrayListOf()

    fun submitList(list: List<Movie>) {
        val oldList = movies
        val diffResult: DiffUtil.DiffResult =
            DiffUtil.calculateDiff(MovieDiffCallback(oldList, list))
        movies = list
        diffResult.dispatchUpdatesTo(this)
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

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieDiffCallback(
        private var oldList: List<Movie>,
        private var newList: List<Movie>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}