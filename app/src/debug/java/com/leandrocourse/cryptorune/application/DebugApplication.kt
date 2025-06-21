package com.leandrocourse.cryptorune.application

import com.leandrocourse.cryptorune.debugtools.FlipperSetup
import timber.log.Timber


class DebugApplication : MainApplication() {

    override fun onCreate() {
        super.onCreate()
        FlipperSetup.start()
        Timber.plant(Timber.DebugTree())
    }
}
