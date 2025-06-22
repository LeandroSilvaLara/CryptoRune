package com.leandrocourse.cryptorune.di

import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.core.FlipperPlugin
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.leandrocourse.core.data.remote.di.COIN_API_INTERCEPTORS_QUALIFIER
import com.leandrocourse.core.data.remote.network.interceptor.ApiKeyInterceptor
import com.leandrocourse.core.data.remote.network.interceptor.HeadersInterceptor
import com.leandrocourse.cryptorune.core.data.remote.BuildConfig
import com.leandrocourse.cryptorune.debugtools.SharedPreferencesPlugin.getDescriptors
import com.leandrocourse.libraries.arch.koin.koinshot.KoinShot
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module


const val FLIPPER_PLUGINS_QUALIFIER = "FLIPPER_PLUGINS_QUALIFIER"

class MainModuleShot : KoinShot() {

    override val dataModule = module {
        single(named(COIN_API_INTERCEPTORS_QUALIFIER)) {
            listOf<Interceptor>(
                ApiKeyInterceptor(BuildConfig.COIN_API_KEY),
                HeadersInterceptor(androidApplication()),
                get<FlipperOkhttpInterceptor>()
            )
        }
    }

    override val additionalModule = module {
        single { NetworkFlipperPlugin() }
        single { AndroidFlipperClient.getInstance(androidContext()) }
        single { FlipperOkhttpInterceptor(get<NetworkFlipperPlugin>(), true) }
        single(named(FLIPPER_PLUGINS_QUALIFIER)) {
            listOf<FlipperPlugin>(
                SharedPreferencesFlipperPlugin(androidContext(), getDescriptors(androidContext())),
                InspectorFlipperPlugin(androidContext(), DescriptorMapping.withDefaults()),
                DatabasesFlipperPlugin(androidContext()),
                NavigationFlipperPlugin.getInstance(),
                CrashReporterPlugin.getInstance(),
                LeakCanary2FlipperPlugin(),
                get<NetworkFlipperPlugin>()
            )
        }
    }
}