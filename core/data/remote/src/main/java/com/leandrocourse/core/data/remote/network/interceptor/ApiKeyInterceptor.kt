package com.leandrocourse.core.data.remote.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val API_KEY_PARAM = "X-CoinAPI-Key"

class ApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(API_KEY_PARAM, apiKey)
            .build()

        return chain.proceed(request)
    }
}
