package com.su.amovie.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private const val API_KEY = "ec9af24f289764814477679301a30e7c"

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor Interceptor@{ chain ->
                    val url = chain.request()
                        .url
                        .newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build()

                    val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()

                    return@Interceptor chain.proceed(request)
                }
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor Interceptor@{ chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}