package com.task.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.movieapp.domain.movie.MovieInteractor
import com.task.movieapp.domain.movie.model.Movie
import com.task.movieapp.utils.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private val _movies: MutableLiveData<Request<List<Movie>>> = MutableLiveData()

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getPopularMovies(page).collect {
                withContext(Dispatchers.Main) {
                    _movies.value = it
                }
            }
        }
    }

    val popularMovies: LiveData<Request<List<Movie>>> = _movies
}