package com.leandrocourse.libraries.testing.rules

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.GlobalContext.getKoinApplicationOrNull
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.module.Module

class KoinTestRule(
    private val modules: List<Module>
) : TestWatcher() {

}
