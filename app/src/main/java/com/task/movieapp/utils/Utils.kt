package com.task.movieapp.utils

import android.view.View
import java.text.DecimalFormat

fun concat(list: List<String>, char: String): String? {
    val builder by lazy { StringBuilder() }
    list.forEachIndexed { index, s ->
        if (s != "") {
            builder.append(s)
            if (index != list.lastIndex) {
                builder.append(char)
            }
        }
    }
    return if (builder.isNotEmpty()) builder.toString() else null
}

fun getDuration(runTime: Int): String {
    val hours = (runTime / 60).let { if (it < 10) "0$it" else it }
    val minutes = (runTime % 60).let { if (it < 10) "0$it" else it }
    return "$hours:$minutes"
}

val formatter: DecimalFormat = DecimalFormat("#,###,###")

private var isClickEnabled: Boolean = true
private val enable: () -> Unit = { isClickEnabled = true }
fun View.setOnDebouncedClickListener(action: () -> Unit) {
    this.setOnClickListener { v ->
        if (isClickEnabled) {
            isClickEnabled = false
            v.postDelayed(enable, 300L)
            action.invoke()
        }
    }
}