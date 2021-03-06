package com.task.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.movieapp.R
import com.task.movieapp.databinding.HomeFragmentBinding
import com.task.movieapp.utils.Request
import com.task.movieapp.domain.movie.model.Movie
import com.task.movieapp.ui.details.DetailsFragment
import com.task.movieapp.utils.gone
import com.task.movieapp.utils.showSnackBar
import com.task.movieapp.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var movieAdapter: MoviesAdapter
    private var loadedMovies: ArrayList<Movie> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            binding.root.showSnackBar(it)
            when (it) {
                is Request.Loading -> binding.progressBar.visible()
                is Request.Error -> binding.progressBar.gone()
                is Request.Success<List<Movie>> -> updateViews(it.data)
            }
        }
    }

    private fun initViews() {
        binding.recMovies.apply {
            movieAdapter = MoviesAdapter(::openDetailsFragment)
            this.adapter = movieAdapter
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                private var pageNumber = 1
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layManager = recyclerView.layoutManager as GridLayoutManager
                    val bottomPosition = layManager.findLastCompletelyVisibleItemPosition()
                    val itemCount = recyclerView.adapter?.itemCount
                    itemCount?.let {
                        if (it > 2 && bottomPosition > it - 3) {
                            pageNumber++
                            viewModel.loadPopularMovies(pageNumber)
                        }
                    }
                }
            })
        }
    }

    private fun updateViews(movies: List<Movie>) {
        loadedMovies.addAll(movies)
        binding.progressBar.gone()
        movieAdapter.submitList(loadedMovies)
    }

    private fun openDetailsFragment(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, DetailsFragment.newInstance(movieId))
            .addToBackStack(null)
            .commit()
    }
}