package com.task.movieapp.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {

    companion object {
        private const val API_KEY = "175b33c7cf5e26d6f54660246cfa4e28"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}
