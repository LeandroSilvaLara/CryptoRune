package com.leandrocourse.libraries.arch

import com.leandrocourse.libraries.arch.di.ArchModuleShot
import com.leandrocourse.libraries.arch.navigation.FeatureRoute
import io.mockk.mockk
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

internal class ArchModuleShotTest : KoinTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `checkModules Should Koin provides dependencies When invoke RemoteModuleShot`() {
        val mockedModules = module {
            factory<FeatureRoute> { mockk(relaxed = true) }
        }

        startKoin {
            modules(ArchModuleShot().modules + mockedModules)
        }.checkModules()
    }
}