package com.leandrocourse.features.details.di

import com.leandrocourse.core.navigation.DetailsNavigation
import com.leandrocourse.features.details.navigation.DetailsNavigationImpl
import com.leandrocourse.libraries.arch.koin.koinshot.KoinShot
import com.leandrocourse.libraries.arch.navigation.extension.provideFeatureRoute
import org.koin.core.module.Module
import org.koin.dsl.module

internal class DetailsModuleShot : KoinShot() {

    override val additionalModule: Module = module {
        provideFeatureRoute<DetailsNavigation> { DetailsNavigationImpl() }
    }
}