package com.task.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.movieapp.domain.movie.MovieInterceptor
import com.task.movieapp.domain.movie.model.Movie
import com.task.movieapp.utils.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val interceptor: MovieInterceptor by inject()
    private val _movies: MutableLiveData<Request<List<Movie>>> = MutableLiveData()

    fun loadPopularMovies(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            interceptor.getPopularMovies(page).collect {
                withContext(Dispatchers.Main) {
                    _movies.value = it
                }
            }
        }
    }

    val popularMovies: LiveData<Request<List<Movie>>> = _movies
}