package com.hako.base.domain.network

import com.hako.base.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val TIMEOUT_IN_SECONDS = 60L

class RemoteClient(endpoint: String) {
    private val logger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.d(message)
        }
    }).setLevel(getLoggerLevel())

    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(endpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()

    fun <T> getClient(api: Class<T>): T = retrofit.create(api)

    private fun getLoggerLevel() = when (BuildConfig.DEBUG) {
        true -> HttpLoggingInterceptor.Level.BASIC
        false -> HttpLoggingInterceptor.Level.NONE
    }
}