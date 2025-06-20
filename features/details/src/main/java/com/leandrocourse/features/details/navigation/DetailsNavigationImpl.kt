package com.leandrocourse.features.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.toRoute
import com.leandrocourse.core.navigation.DetailsNavigation
import com.leandrocourse.core.navigation.DetailsRoute
import com.leandrocourse.features.details.presentation.components.DetailsScreen
import com.leandrocourse.libraries.arch.navigation.extension.animatedComposable

internal class DetailsNavigationImpl : DetailsNavigation {

    override fun register(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.animatedComposable<DetailsRoute> {
            val args = it.toRoute<DetailsRoute>()
            DetailsScreen(args.exchangeId)
        }
    }
}