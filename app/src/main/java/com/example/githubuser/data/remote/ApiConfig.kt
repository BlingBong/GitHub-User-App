package com.example.githubuser.data.remote

import com.example.githubuser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor {
                    if(BuildConfig.DEBUG) {
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    } else {
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
                    }

                    val original = it.request()
                    val requestBuilder = original.newBuilder()
                        .addHeader("Authorization", "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")
                    val request = requestBuilder.build()
                    it.proceed(request)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}