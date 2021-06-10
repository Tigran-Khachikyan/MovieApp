package com.task.movieapp.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.task.movieapp.R
import com.task.movieapp.utils.Request
import com.task.movieapp.databinding.DetailsFragmentBinding
import com.task.movieapp.domain.movie.model.Movie
import com.task.movieapp.utils.*

class DetailsFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "MovieId"

        fun newInstance(movieId: Int): DetailsFragment {
            return DetailsFragment().apply {
                val bundle = Bundle()
                bundle.putInt(MOVIE_ID, movieId)
                this.arguments = bundle
            }
        }
    }

    private lateinit var binding: DetailsFragmentBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        viewModel.movieDetails.observe(viewLifecycleOwner) {
            binding.root.showSnackBar(it)
            when (it) {
                is Request.Loading -> binding.progressBar.visible()
                is Request.Error -> binding.progressBar.gone()
                is Request.Success<Movie> -> initViews(it.data)
            }
        }
    }

    private fun loadData() {
        val id = arguments?.getInt(MOVIE_ID)
        id?.let { viewModel.loadDetails(it) }
    }

    private fun initViews(movie: Movie) {
        binding.progressBar.gone()
        binding.tvTitle.text = movie.title
        movie.overview?.let { binding.tvAbout.text = it } ?: binding.tvAbout.gone()
        val genres = concat(movie.genres, ", ")
        genres?.let { binding.tvGenresAdult.text = it } ?: binding.tvGenresAdult.gone()
        if (movie.adult) {
            binding.tvGenresAdult.setCompoundDrawablesWithIntrinsicBounds(
                0, 0, R.drawable.ic_plus_18_movie, 0
            )
        }
        Picasso.get().load(movie.backdropPath).into(binding.ivBackground)

        movie.rating?.let { binding.tvRating.text = it.toString() } ?: binding.tvRating.gone()

        movie.releaseDate?.let {
            val date = "${getString(R.string.released_in)} $it"
            binding.tvReleaseDate.text = date
        } ?: binding.tvReleaseDate.gone()

        movie.budget?.let {
            val budgetInfo = "${getString(R.string.budget)}  ${formatter.format(it)} $"
            binding.tvBudget.text = budgetInfo
        } ?: binding.tvBudget.gone()

        movie.duration?.let {
            val dur = "${getString(R.string.duration)}   ${getDuration(it)}"
            binding.tvDuration.text = dur
        } ?: binding.tvDuration.gone()

        val spokenLanguages = movie.spokenLanguages?.let { concat(it, ", ") }
        spokenLanguages?.let {
            val spoken = "${getString(R.string.spoken_languages)} : $it"
            binding.tvSpokenLanguages.text = spoken
        } ?: binding.tvSpokenLanguages.gone()

        movie.originalLanguage?.let {
            val orig = "${getString(R.string.original_language)} : $it"
            binding.tvOrigLanguage.text = orig
        } ?: binding.tvOrigLanguage.gone()

        val companies = movie.productionCompanies?.let { concat(it, ", ") }
        companies?.let {
            val comp = "${getString(R.string.companies)} : $it"
            binding.tvCompanies.text = comp
        } ?: binding.tvCompanies.gone()

        val countries = movie.productionCountries?.let { concat(it, ", ") }
        countries?.let {
            val comp = "${getString(R.string.countries)} : $it"
            binding.tvCountries.text = comp
        } ?: binding.tvCountries.gone()
    }


}