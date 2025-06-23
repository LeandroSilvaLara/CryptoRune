package com.leandrocourse.features.details.di

import com.leandrocourse.core.domain.repository.MBCryptoRuneRepository
import com.leandrocourse.features.details.presentation.viewmodel.DetailsViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.plus

internal class DetailsModuleLoadTest2 : KoinTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should check Koin modules with correct parameter type`() {
        val exchangeId = "MERCADOBITCOIN"

        val mockedModules = module {
            factory<MBCryptoRuneRepository> { mockk(relaxed = true) }
        }

        startKoin {
            modules(DetailsModuleLoad.modules + mockedModules)
        }.checkModules {
            withParameters<DetailsViewModel> { parametersOf(exchangeId) }
        }
    }
}
