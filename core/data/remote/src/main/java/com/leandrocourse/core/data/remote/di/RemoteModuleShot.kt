package com.leandrocourse.core.data.remote.di

import com.leandrocourse.core.data.remote.network.HttpClient
import com.leandrocourse.core.data.remote.network.HttpClientImpl
import com.leandrocourse.core.data.remote.network.retrofit.RetrofitClient
import com.leandrocourse.cryptorune.core.data.remote.BuildConfig
import com.leandrocourse.libraries.arch.koin.koinshot.KoinShot
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val COIN_API_INTERCEPTORS_QUALIFIER = "COIN_API_INTERCEPTORS_QUALIFIER"
const val COIN_API_RETROFIT_QUALIFIER = "COIN_API_RETROFIT_QUALIFIER"
const val COIN_API_HTTP_CLIENT_QUALIFIER = "COIN_API_HTTP_CLIENT_QUALIFIER"

internal class RemoteModuleShot : KoinShot() {

    override val dataModule: Module = module {
        single<Retrofit>(named(COIN_API_RETROFIT_QUALIFIER)) {
            RetrofitClient(
                baseUrl = BuildConfig.BASE_URL,
                interceptors = get(named(COIN_API_INTERCEPTORS_QUALIFIER))
            ).create()
        }
        single<HttpClient>(named(COIN_API_HTTP_CLIENT_QUALIFIER)) {
            HttpClientImpl(retrofit = get(named(COIN_API_RETROFIT_QUALIFIER)))
        }
    }
}
