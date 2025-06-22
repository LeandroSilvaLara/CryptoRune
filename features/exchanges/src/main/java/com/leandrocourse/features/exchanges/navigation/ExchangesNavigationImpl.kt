package com.leandrocourse.features.exchanges.navigation

import androidx.navigation.NavGraphBuilder
import com.leandrocourse.core.navigation.ExchangesNavigation
import com.leandrocourse.core.navigation.ExchangesRoute
import com.leandrocourse.features.exchanges.presentation.screen.ExchangesScreen
import com.leandrocourse.libraries.arch.navigation.extension.animatedComposable

internal class ExchangesNavigationImpl : ExchangesNavigation {

    override fun register(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.animatedComposable<ExchangesRoute> {
            ExchangesScreen()
        }
    }
}