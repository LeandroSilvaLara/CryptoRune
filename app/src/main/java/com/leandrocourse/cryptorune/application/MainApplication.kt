package com.leandrocourse.cryptorune.application

import android.app.Application
import android.content.Context
import org.koin.core.context.stopKoin
import kotlin.collections.get

open class MainApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin(KoinDeclaration.get(this))
    }
}
