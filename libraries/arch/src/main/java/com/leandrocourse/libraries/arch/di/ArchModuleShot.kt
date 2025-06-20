package com.leandrocourse.libraries.arch.di

import com.leandrocourse.libraries.arch.koin.koinshot.KoinShot
import com.leandrocourse.libraries.arch.navigation.FeatureRoute
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val FEATURE_ROUTES_QUALIFIER = "feature_routes"

internal class ArchModuleShot : KoinShot() {

    override val additionalModule = module {
        single(named(FEATURE_ROUTES_QUALIFIER)) {
            getAll<FeatureRoute>()
        }
    }
}