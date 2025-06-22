package com.leandrocourse.cryptorune.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.leandrocourse.core.navigation.ExchangesRoute
import com.leandrocourse.libraries.arch.navigation.FeatureRoute

@Composable
fun MainNavGraph(
    navController: NavHostController,
    featureRoutes: List<FeatureRoute>
) {

    NavHost(
        navController = navController,
        startDestination = ExchangesRoute
    ) {
        featureRoutes.forEach { featureRoute ->
            featureRoute.register(navGraphBuilder = this)
        }
    }
}
