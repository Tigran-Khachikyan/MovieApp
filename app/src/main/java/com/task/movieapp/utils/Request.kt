package com.task.movieapp.utils

sealed class Request<out T> {

    object Loading : Request<Nothing>()

    sealed class Success<T>(open val data: T) : Request<T>() {
        class FromNetwork<T>(override val data: T) : Success<T>(data)
        class FromCache<T>(override val data: T) : Success<T>(data)
    }

    sealed class Error : Request<Nothing>() {
        object EmptyResponse : Error()
        object ResponseNotSucceed : Error()
        object ConnectionError : Error()
        object EmptyCache : Error()
    }
}