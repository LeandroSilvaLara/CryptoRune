package com.leandrocourse.core.navigation

import com.leandrocourse.libraries.arch.navigation.FeatureRoute
import com.leandrocourse.libraries.arch.navigation.NavigateRoute
import kotlinx.serialization.Serializable

interface ExchangesNavigation : FeatureRoute, NavigateRoute<ExchangesRoute>

@Serializable
object ExchangesRoute