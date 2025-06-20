package com.leandrocourse.core.navigation

import com.leandrocourse.libraries.arch.navigation.FeatureRoute
import com.leandrocourse.libraries.arch.navigation.NavigateRoute
import kotlinx.serialization.Serializable

interface DetailsNavigation : FeatureRoute, NavigateRoute<DetailsRoute>

@Serializable
data class DetailsRoute(
    val exchangeId: String
)