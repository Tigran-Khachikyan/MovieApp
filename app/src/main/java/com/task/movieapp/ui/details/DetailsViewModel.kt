package com.task.movieapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.movieapp.utils.Request
import com.task.movieapp.domain.movie.MovieInteractor
import com.task.movieapp.domain.movie.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private val _details = MutableLiveData<Request<Movie>>()

    fun loadDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getMovieDetails(id).collect {
                withContext(Dispatchers.Main) {
                    _details.value = it
                }
            }
        }
    }

    val movieDetails: LiveData<Request<Movie>> = _details
}