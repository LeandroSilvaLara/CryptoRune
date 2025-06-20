package com.leandrocourse.core.data.remote.network.retrofit

import com.leandrocourse.cryptorune.core.data.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.collections.forEach


private const val TAG = "OkHttp"
private const val READ_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 10L
private const val CONNECT_TIMEOUT = 15L

internal class RetrofitClient(
    private val baseUrl: String,
    private val interceptors: List<Interceptor>
) {

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient())
            .build()
    }

    private fun okhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .apply { interceptors.forEach { addInterceptor(it) } }
            .build()
    }

    private val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
        if (BuildConfig.DEBUG) Timber.tag(TAG).d(message)
    }.apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else HttpLoggingInterceptor.Level.NONE
    }
}
