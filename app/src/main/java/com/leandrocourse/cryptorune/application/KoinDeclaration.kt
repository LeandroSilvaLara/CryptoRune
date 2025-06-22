package com.leandrocourse.cryptorune.application

import android.app.Application
import com.leandrocourse.cryptorune.core.data.remote.BuildConfig
import com.leandrocourse.libraries.arch.koin.koinshot.ModuleInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration
import timber.log.Timber

object KoinDeclaration {

    fun get(application: Application): KoinAppDeclaration = {
        Timber.plant(Timber.DebugTree())
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(application)
        setupKoinShot()
    }

    private fun KoinApplication.setupKoinShot() {
        modules(ModuleInitializer.modules.toList())
    }
}
