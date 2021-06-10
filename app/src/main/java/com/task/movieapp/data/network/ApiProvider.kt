package com.task.movieapp.data.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiProvider(
        private val interceptors: List<Interceptor> = emptyList(),
        private val gson: Gson
) {

    private lateinit var movieApi: MovieApi

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    fun init() {
        initMyServiceApi()
    }

    fun getMyServiceApi(): MovieApi = movieApi

    private fun initMyServiceApi() {
        movieApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(
                        OkHttpClient.Builder()
                                .apply {
                                    connectTimeout(60, TimeUnit.SECONDS)
                                    readTimeout(60, TimeUnit.SECONDS)
                                    writeTimeout(60, TimeUnit.SECONDS)
                                    interceptors.forEach { addInterceptor(it) }
                                }
                                .build()
                )
                .build()
                .create(MovieApi::class.java)
    }
}