package com.task.movieapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.task.movieapp.R

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}


fun View.showSnackBar(state: Request<*>) {
    val problemInfoRes = when (state) {
        is Request.Loading -> null
        is Request.Success.FromCache<*> -> R.string.from_cache
        is Request.Success.FromNetwork<*> -> R.string.latest_data
        is Request.Error.EmptyResponse -> R.string.empty_response
        is Request.Error.ResponseNotSucceed -> R.string.response_not_succeed
        is Request.Error.ConnectionError -> R.string.request_failed
        is Request.Error.EmptyCache -> R.string.cache_is_empty
    }
    val problemInfo = problemInfoRes?.let { context.getString(it) }
    problemInfo?.let { Snackbar.make(this, it, 3000).show() }
}