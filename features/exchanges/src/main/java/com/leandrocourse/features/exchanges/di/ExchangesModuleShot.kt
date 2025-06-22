package com.leandrocourse.features.exchanges.di

import com.leandrocourse.core.navigation.ExchangesNavigation
import com.leandrocourse.features.exchanges.navigation.ExchangesNavigationImpl
import com.leandrocourse.libraries.arch.koin.koinshot.KoinShot
import com.leandrocourse.libraries.arch.navigation.extension.provideFeatureRoute
import org.koin.core.module.Module
import org.koin.dsl.module

internal class ExchangesModuleShot : KoinShot() {

    override val additionalModule: Module = module {
        provideFeatureRoute<ExchangesNavigation> { ExchangesNavigationImpl() }
    }
}