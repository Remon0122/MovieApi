package com.example.movieapi.data.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    val API_KEY  = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMTQ0M2ZhYjE0ODE3OWM1NmZkNTk1OTMxNzM0N2EyMyIsIm5iZiI6MTY5ODE1NDI4NC40MjIwMDAyLCJzdWIiOiI2NTM3YzcyYzdmY2FiMzAwZWFiM2Q3N2MiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.2qBYczePWNfNWepMI2aaqkikFIQMTzYMxhGb4rjSFsk"
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newRequest = original.newBuilder()
            .addHeader("Authorization", "Bearer $API_KEY")
            .build()
        return chain.proceed(newRequest)
    }
}