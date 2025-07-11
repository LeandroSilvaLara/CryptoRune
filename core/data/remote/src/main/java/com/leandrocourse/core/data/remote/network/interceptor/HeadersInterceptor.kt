package com.leandrocourse.core.data.remote.network.interceptor

import android.content.Context
import android.os.Build
import com.leandrocourse.cryptorune.core.data.remote.BuildConfig
import com.leandrocourse.cryptorune.core.data.remote.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val USER_AGENT_FORMAT = "%1\$s/%2\$s (Android %3\$s; %4\$s)"

class HeadersInterceptor(
    private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val userAgent = context.userAgent()
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("User-Agent", userAgent)
            .build()

        return chain.proceed(request)
    }

    private fun Context.userAgent(): String {
        return String.format(
            USER_AGENT_FORMAT,
            getString(R.string.app_name),
            BuildConfig.APP_VERSION,
            Build.VERSION.RELEASE,
            Build.MODEL
        )
    }
}